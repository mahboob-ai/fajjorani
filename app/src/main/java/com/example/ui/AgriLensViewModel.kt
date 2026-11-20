package com.example.ui

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ai.AgriLensAiService
import com.example.data.local.AgriLensDatabase
import com.example.data.local.HistoryRepository
import com.example.data.local.ImageStorageHelper
import com.example.data.local.ImageValidationResult
import com.example.data.local.PreferencesRepository
import com.example.data.model.ChatMessage
import com.example.data.model.CropCatalogRepository
import com.example.data.model.CropCategory
import com.example.data.model.CropInfo
import com.example.data.model.DiagnosisAssessment
import com.example.data.model.PresentationDeck
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "AgriLensViewModel"

sealed interface AnalysisState {
    data object Idle : AnalysisState
    data object Loading : AnalysisState
    data class Success(val assessment: DiagnosisAssessment) : AnalysisState
    data class Error(val message: String) : AnalysisState
}

data class DiagnosisUiState(
    val selectedCategory: CropCategory? = null,
    val selectedCrop: CropInfo? = CropCatalogRepository.findByName("Tomato"),
    val cropSearchQuery: String = "",
    val preparedImagePath: String? = null,
    val preparedImageSizeKb: Long = 0L,
    val preparedMimeType: String = "",
    val isPreparingImage: Boolean = false,
    val imageValidationError: String? = null,
    val analysisState: AnalysisState = AnalysisState.Idle
)

data class ExpertUiState(
    val draftMessage: String = "",
    val attachedImagePath: String? = null,
    val attachedImageSizeKb: Long = 0L,
    val isPreparingImage: Boolean = false,
    val imageError: String? = null,
    val isStreaming: Boolean = false,
    val streamingReplyText: String = "",
    val apiErrorMessage: String? = null,
    val showFallbackFaqs: Boolean = false
)

data class LibraryUiState(
    val searchQuery: String = "",
    val selectedCategory: CropCategory? = null,
    val selectedCrop: CropInfo? = null
)

class AgriLensViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AgriLensDatabase.getInstance(application)
    private val historyRepository = HistoryRepository(
        diagnosisDao = database.diagnosisDao(),
        chatDao = database.chatDao()
    )
    private val preferencesRepository = PreferencesRepository(application)
    private val aiService = AgriLensAiService()

    val diagnosisHistory: StateFlow<List<DiagnosisAssessment>> =
        historyRepository.diagnosisHistory.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = emptyList()
        )

    val chatMessages: StateFlow<List<ChatMessage>> =
        historyRepository.chatHistory.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = emptyList()
        )

    val expertLanguage: StateFlow<String> =
        preferencesRepository.expertLanguage.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = "English"
        )

    val showSpeakerNotes: StateFlow<Boolean> =
        preferencesRepository.showSpeakerNotes.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = true
        )

    private val _diagnosisState = MutableStateFlow(DiagnosisUiState())
    val diagnosisState: StateFlow<DiagnosisUiState> = _diagnosisState.asStateFlow()

    private val _expertState = MutableStateFlow(ExpertUiState())
    val expertState: StateFlow<ExpertUiState> = _expertState.asStateFlow()

    private val _libraryState = MutableStateFlow(LibraryUiState())
    val libraryState: StateFlow<LibraryUiState> = _libraryState.asStateFlow()

    private val _selectedHistoryRecord = MutableStateFlow<DiagnosisAssessment?>(null)
    val selectedHistoryRecord: StateFlow<DiagnosisAssessment?> = _selectedHistoryRecord.asStateFlow()

    private val _currentSlideIndex = MutableStateFlow(0)
    val currentSlideIndex: StateFlow<Int> = _currentSlideIndex.asStateFlow()

    // --- DIAGNOSIS ACTIONS ---

    fun selectDiagnosisCategory(category: CropCategory?) {
        _diagnosisState.update { state ->
            val currentCropMatches = state.selectedCrop != null &&
                (category == null || state.selectedCrop.category == category)
            val fallbackCrop = if (currentCropMatches) {
                state.selectedCrop
            } else {
                CropCatalogRepository.cropsByCategory(category).firstOrNull()
            }
            state.copy(
                selectedCategory = category,
                selectedCrop = fallbackCrop
            )
        }
    }

    fun selectDiagnosisCrop(crop: CropInfo) {
        _diagnosisState.update {
            it.copy(
                selectedCrop = crop,
                cropSearchQuery = "",
                analysisState = if (it.analysisState is AnalysisState.Error) AnalysisState.Idle else it.analysisState
            )
        }
    }

    fun updateDiagnosisCropSearch(query: String) {
        _diagnosisState.update { it.copy(cropSearchQuery = query) }
    }

    fun onDiagnosisImageSelected(uri: Uri) {
        viewModelScope.launch {
            _diagnosisState.update {
                it.copy(isPreparingImage = true, imageValidationError = null)
            }
            val result = ImageStorageHelper.validateAndPrepareImage(
                context = getApplication(),
                uri = uri,
                subfolder = "leaf_history"
            )
            when (result) {
                is ImageValidationResult.Valid -> {
                    if (!result.bitmap.isRecycled) {
                        result.bitmap.recycle()
                    }
                    _diagnosisState.update {
                        it.copy(
                            preparedImagePath = result.localFilePath,
                            preparedImageSizeKb = result.displaySizeKb,
                            preparedMimeType = result.mimeType,
                            isPreparingImage = false,
                            imageValidationError = null,
                            analysisState = AnalysisState.Idle
                        )
                    }
                }
                is ImageValidationResult.Invalid -> {
                    _diagnosisState.update {
                        it.copy(
                            isPreparingImage = false,
                            imageValidationError = result.errorMessage
                        )
                    }
                }
            }
        }
    }

    fun removeDiagnosisImage() {
        val previousPath = _diagnosisState.value.preparedImagePath
        // Only delete if not yet saved in history
        val isSavedInHistory = diagnosisHistory.value.any { it.imageFilePath == previousPath }
        if (!isSavedInHistory) {
            ImageStorageHelper.deleteFileQuietly(previousPath)
        }
        _diagnosisState.update {
            it.copy(
                preparedImagePath = null,
                preparedImageSizeKb = 0L,
                preparedMimeType = "",
                imageValidationError = null,
                analysisState = AnalysisState.Idle
            )
        }
    }

    fun analyseLeaf() {
        val current = _diagnosisState.value
        val crop = current.selectedCrop
        val imagePath = current.preparedImagePath

        if (crop == null) {
            _diagnosisState.update {
                it.copy(analysisState = AnalysisState.Error("Please select a crop before analyzing."))
            }
            return
        }
        if (imagePath.isNullOrBlank()) {
            _diagnosisState.update {
                it.copy(analysisState = AnalysisState.Error("Please upload or capture a leaf photograph first."))
            }
            return
        }

        viewModelScope.launch {
            _diagnosisState.update { it.copy(analysisState = AnalysisState.Loading) }
            var loadedBitmap: android.graphics.Bitmap? = null
            try {
                loadedBitmap = ImageStorageHelper.loadBitmapFromPath(imagePath)
                    ?: throw IllegalStateException("Could not load leaf image from local storage.")

                val assessment = aiService.analyzeLeafImage(
                    cropName = crop.name,
                    cropCategory = crop.category.displayName,
                    localImagePath = imagePath,
                    bitmap = loadedBitmap
                )
                val savedId = historyRepository.saveDiagnosis(assessment)
                val persisted = assessment.copy(id = savedId)
                _diagnosisState.update {
                    it.copy(analysisState = AnalysisState.Success(persisted))
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(TAG, "analyseLeaf error", e)
                _diagnosisState.update {
                    it.copy(
                        analysisState = AnalysisState.Error(
                            e.localizedMessage
                                ?: "Unable to analyze the leaf image right now. Please check your internet connection and try again."
                        )
                    )
                }
            } finally {
                loadedBitmap?.let {
                    if (!it.isRecycled) it.recycle()
                }
            }
        }
    }

    fun resetDiagnosisForAnotherLeaf() {
        _diagnosisState.update {
            it.copy(
                preparedImagePath = null,
                preparedImageSizeKb = 0L,
                preparedMimeType = "",
                imageValidationError = null,
                analysisState = AnalysisState.Idle
            )
        }
    }

    // --- AI CROP EXPERT ACTIONS ---

    fun updateExpertDraft(text: String) {
        _expertState.update { it.copy(draftMessage = text) }
    }

    fun onExpertImageSelected(uri: Uri) {
        viewModelScope.launch {
            _expertState.update { it.copy(isPreparingImage = true, imageError = null) }
            val result = ImageStorageHelper.validateAndPrepareImage(
                context = getApplication(),
                uri = uri,
                subfolder = "chat_photos"
            )
            when (result) {
                is ImageValidationResult.Valid -> {
                    if (!result.bitmap.isRecycled) result.bitmap.recycle()
                    _expertState.update {
                        it.copy(
                            attachedImagePath = result.localFilePath,
                            attachedImageSizeKb = result.displaySizeKb,
                            isPreparingImage = false,
                            imageError = null
                        )
                    }
                }
                is ImageValidationResult.Invalid -> {
                    _expertState.update {
                        it.copy(
                            isPreparingImage = false,
                            imageError = result.errorMessage
                        )
                    }
                }
            }
        }
    }

    fun removeExpertAttachedImage() {
        _expertState.update {
            it.copy(attachedImagePath = null, attachedImageSizeKb = 0L, imageError = null)
        }
    }

    fun sendExpertMessage(overridePrompt: String? = null) {
        val currentState = _expertState.value
        if (currentState.isStreaming) return

        val question = (overridePrompt ?: currentState.draftMessage).trim()
        val imagePath = currentState.attachedImagePath
        if (question.isBlank() && imagePath == null) return

        val finalQuestion = question.ifBlank {
            "Please examine this plant photograph and tell me what visible issues or care tips you notice."
        }

        viewModelScope.launch {
            val priorHistory = chatMessages.value
            val userMsg = ChatMessage(
                role = "user",
                text = finalQuestion,
                imageFilePath = imagePath
            )
            historyRepository.saveChatMessage(userMsg)

            _expertState.update {
                it.copy(
                    draftMessage = if (overridePrompt == null) "" else it.draftMessage,
                    attachedImagePath = null,
                    attachedImageSizeKb = 0L,
                    isStreaming = true,
                    streamingReplyText = "",
                    apiErrorMessage = null
                )
            }

            var bitmap: android.graphics.Bitmap? = null
            try {
                if (!imagePath.isNullOrBlank()) {
                    bitmap = ImageStorageHelper.loadBitmapFromPath(imagePath)
                }

                var finalReply = ""
                aiService.streamExpertReply(
                    priorHistory = priorHistory,
                    userQuestion = finalQuestion,
                    attachedImage = bitmap,
                    preferredLanguage = expertLanguage.value
                ).collect { partial ->
                    finalReply = partial
                    _expertState.update { it.copy(streamingReplyText = partial) }
                }

                if (finalReply.isNotBlank()) {
                    historyRepository.saveChatMessage(
                        ChatMessage(role = "model", text = finalReply)
                    )
                }
                _expertState.update {
                    it.copy(isStreaming = false, streamingReplyText = "")
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(TAG, "sendExpertMessage error", e)
                _expertState.update {
                    it.copy(
                        isStreaming = false,
                        streamingReplyText = "",
                        apiErrorMessage = e.localizedMessage
                            ?: "Gemini AI Expert is temporarily unreachable. You can review the offline educational FAQs below.",
                        showFallbackFaqs = true
                    )
                }
            } finally {
                bitmap?.let { if (!it.isRecycled) it.recycle() }
            }
        }
    }

    fun prepareExpertQuestionFromDiagnosis(assessment: DiagnosisAssessment) {
        val summaryPrompt = buildString {
            append("I just ran a preliminary AgriLens leaf screening for ${assessment.cropName}. ")
            append("The screening noted: \"${assessment.possibleCondition}\" (${assessment.assessmentStatus}). ")
            if (assessment.observedSymptoms.isNotEmpty()) {
                append("Observed symptoms: ${assessment.observedSymptoms.joinToString("; ")}. ")
            }
            append("Can you explain what might be happening and safe prevention or care steps?")
        }
        _expertState.update {
            it.copy(
                draftMessage = summaryPrompt,
                attachedImagePath = assessment.imageFilePath.takeIf { path -> path.isNotBlank() },
                apiErrorMessage = null
            )
        }
    }

    fun prepareExpertQuestionForCrop(crop: CropInfo) {
        _expertState.update {
            it.copy(
                draftMessage = "Tell me key care tips and early disease warning signs to watch for in ${crop.name}.",
                apiErrorMessage = null
            )
        }
    }

    fun setExpertLanguage(language: String) {
        viewModelScope.launch {
            preferencesRepository.setExpertLanguage(language)
        }
    }

    fun clearChatHistory() {
        viewModelScope.launch {
            historyRepository.clearAllChat()
            _expertState.update {
                it.copy(apiErrorMessage = null, streamingReplyText = "")
            }
        }
    }

    fun toggleFallbackFaqs() {
        _expertState.update { it.copy(showFallbackFaqs = !it.showFallbackFaqs) }
    }

    fun dismissExpertError() {
        _expertState.update { it.copy(apiErrorMessage = null) }
    }

    // --- LIBRARY ACTIONS ---

    fun updateLibrarySearch(query: String) {
        _libraryState.update { it.copy(searchQuery = query) }
    }

    fun selectLibraryCategory(category: CropCategory?) {
        _libraryState.update { it.copy(selectedCategory = category) }
    }

    fun selectLibraryCrop(crop: CropInfo?) {
        _libraryState.update { it.copy(selectedCrop = crop) }
    }

    fun prepareDiagnosisForCrop(crop: CropInfo) {
        _diagnosisState.update {
            it.copy(
                selectedCategory = crop.category,
                selectedCrop = crop,
                analysisState = AnalysisState.Idle
            )
        }
    }

    // --- HISTORY ACTIONS ---

    fun openHistoryRecord(record: DiagnosisAssessment?) {
        _selectedHistoryRecord.value = record
    }

    fun deleteDiagnosisRecord(record: DiagnosisAssessment) {
        viewModelScope.launch {
            historyRepository.deleteDiagnosis(record.id)
            if (_selectedHistoryRecord.value?.id == record.id) {
                _selectedHistoryRecord.value = null
            }
            if (_diagnosisState.value.preparedImagePath != record.imageFilePath) {
                ImageStorageHelper.deleteFileQuietly(record.imageFilePath)
            }
        }
    }

    fun clearAllHistory() {
        viewModelScope.launch {
            val activePath = _diagnosisState.value.preparedImagePath
            diagnosisHistory.value.forEach { rec ->
                if (rec.imageFilePath != activePath) {
                    ImageStorageHelper.deleteFileQuietly(rec.imageFilePath)
                }
            }
            historyRepository.clearAllDiagnoses()
            historyRepository.clearAllChat()
            _selectedHistoryRecord.value = null
        }
    }

    // --- PRESENTATION MODE ACTIONS ---

    fun nextSlide() {
        val maxIndex = PresentationDeck.slides.lastIndex
        _currentSlideIndex.update { (it + 1).coerceAtMost(maxIndex) }
    }

    fun previousSlide() {
        _currentSlideIndex.update { (it - 1).coerceAtLeast(0) }
    }

    fun goToSlide(index: Int) {
        _currentSlideIndex.value = index.coerceIn(0, PresentationDeck.slides.lastIndex)
    }

    fun toggleSpeakerNotes() {
        viewModelScope.launch {
            preferencesRepository.setShowSpeakerNotes(!showSpeakerNotes.value)
        }
    }
}
