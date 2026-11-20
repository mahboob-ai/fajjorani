package com.example.data.ai

import android.graphics.Bitmap
import android.util.Log
import com.example.data.model.ChatMessage
import com.example.data.model.DiagnosisAssessment
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.Content
import com.google.firebase.ai.type.Schema
import com.google.firebase.ai.type.content
import com.google.firebase.ai.type.generationConfig
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import org.json.JSONArray
import org.json.JSONObject

private const val TAG = "AgriLensAiService"
private const val MODEL_NAME = "gemini-3.8-flash"
private const val AI_TIMEOUT_MS = 60_000L

class AgriLensAiService {

    private val diagnosisSchema = Schema.obj(
        properties = mapOf(
            "assessmentStatus" to Schema.enumeration(
                values = listOf(
                    "Preliminary Indicators Found",
                    "Healthy Appearance",
                    "Unknown / Inconclusive"
                ),
                description = "Overall preliminary screening category. Choose Unknown / Inconclusive if the image is blurry, dark, not a leaf, or ambiguous."
            ),
            "possibleCondition" to Schema.string(
                description = "Possible plant disease, pest stress, nutrient stress, or 'Unknown / Inconclusive' if uncertain, or 'No clear disease signs visible' if healthy."
            ),
            "imageClarityNote" to Schema.string(
                description = "Honest assessment of photo clarity, lighting, focus, and whether leaf details are clearly visible."
            ),
            "observedSymptoms" to Schema.array(
                items = Schema.string(),
                description = "Visible symptoms observed in the photo (spots, discoloration, curling, lesions, etc.)."
            ),
            "possibleCauses" to Schema.array(
                items = Schema.string(),
                description = "Potential biological or environmental causes (fungal, bacterial, viral, nutrient stress, moisture stress)."
            ),
            "preventionGuidance" to Schema.array(
                items = Schema.string(),
                description = "Safe, general agricultural prevention and hygiene measures without unsafe pesticide dosage prescriptions."
            ),
            "recommendedNextSteps" to Schema.array(
                items = Schema.string(),
                description = "Practical next steps including monitoring and consulting a local agricultural expert or Krishi Vigyan Kendra (KVK)."
            ),
            "limitationsDisclaimer" to Schema.string(
                description = "Clear note stating this is a preliminary Gemini AI visual screening, not a trained CNN classifier or definitive laboratory diagnosis."
            )
        )
    )

    private val diagnosisModel by lazy {
        Firebase.ai.generativeModel(
            modelName = MODEL_NAME,
            generationConfig = generationConfig {
                responseMimeType = "application/json"
                responseSchema = diagnosisSchema
                temperature = 0.2f
            },
            systemInstruction = content {
                text(
                    """
                    You are AgriLens AI's preliminary leaf screening module.
                    CRITICAL RULES:
                    1. You are NOT a trained CNN crop disease classifier and must NEVER claim to be one.
                    2. NEVER fabricate or invent any confidence percentages or accuracy numbers.
                    3. Examine ONLY what is actually visible in the provided photograph for the selected crop.
                    4. If the photo is blurry, poorly lit, shows no plant leaf, or symptoms are ambiguous, set assessmentStatus to "Unknown / Inconclusive" and possibleCondition to "Unknown / Inconclusive".
                    5. Avoid prescribing specific chemical pesticide dosages. Stick to safe, general cultural/preventive agricultural practices and recommend verification by a local agricultural specialist or Krishi Vigyan Kendra (KVK).
                    6. Always distinguish this preliminary Gemini visual assessment from future dedicated CNN model predictions in limitationsDisclaimer.
                    """.trimIndent()
                )
            }
        )
    }

    private val expertModel by lazy {
        Firebase.ai.generativeModel(
            modelName = MODEL_NAME,
            generationConfig = generationConfig {
                temperature = 0.5f
            },
            systemInstruction = content {
                text(
                    """
                    You are AgriLens AI Crop Expert, a farmer-friendly AI agricultural assistant built by Team BioVision.
                    STRICT GUIDELINES:
                    1. Always clearly identify yourself as an AI assistant when relevant—NEVER claim to be a human agronomist.
                    2. Support both simple English and simple Hindi (Devanagari or Roman Hindi if the user writes in Hindi, or according to user's preferred language). Keep language clear, respectful, and easy for farmers and students to understand.
                    3. If the farmer has not specified which crop, location/climate, season, or exact symptoms, politely ask clarifying follow-up questions while still giving helpful general pointers.
                    4. Understand uploaded plant/leaf photographs carefully; mention image clarity limitations if a photo is unclear.
                    5. Explain possible problems step-by-step: visible signs, likely causes, crop-specific care, watering, soil health, seasonal tips, and preventive monitoring.
                    6. NEVER provide unsupported chemical pesticide dosages or unsafe chemical prescriptions. Suggest safe cultural/preventive steps and recommend contacting a local agricultural expert or Krishi Vigyan Kendra (KVK) whenever identification is uncertain or chemical treatment is needed.
                    7. Keep answers structured, concise, and practical.
                    """.trimIndent()
                )
            }
        )
    }

    suspend fun analyzeLeafImage(
        cropName: String,
        cropCategory: String,
        localImagePath: String,
        bitmap: Bitmap
    ): DiagnosisAssessment {
        return try {
            val response = withTimeout(AI_TIMEOUT_MS) {
                diagnosisModel.generateContent(
                    content {
                        image(bitmap)
                        text(
                            "Selected crop: $cropName (Category: $cropCategory). " +
                                "Please examine the uploaded photograph carefully for visible leaf symptoms, " +
                                "possible disease or stress indicators, image clarity, possible causes, " +
                                "general prevention steps, and next steps. If unclear or not a leaf, mark Unknown / Inconclusive."
                        )
                    }
                )
            }
            val jsonText = response.text
                ?: throw IllegalStateException("AI service returned an empty assessment response.")

            val json = JSONObject(jsonText)
            DiagnosisAssessment(
                cropName = cropName,
                cropCategory = cropCategory,
                imageFilePath = localImagePath,
                assessmentStatus = json.optString("assessmentStatus", "Unknown / Inconclusive")
                    .ifBlank { "Unknown / Inconclusive" },
                possibleCondition = json.optString("possibleCondition", "Unknown / Inconclusive")
                    .ifBlank { "Unknown / Inconclusive" },
                imageClarityNote = json.optString(
                    "imageClarityNote",
                    "Image clarity could not be fully verified."
                ),
                observedSymptoms = json.optStringArray("observedSymptoms"),
                possibleCauses = json.optStringArray("possibleCauses"),
                preventionGuidance = json.optStringArray("preventionGuidance"),
                recommendedNextSteps = json.optStringArray("recommendedNextSteps"),
                limitationsDisclaimer = json.optString(
                    "limitationsDisclaimer",
                    "This is a preliminary Gemini AI visual assessment—not a trained CNN classifier or confirmed lab diagnosis. Please verify uncertain findings with a local agricultural expert or KVK."
                )
            )
        } catch (e: TimeoutCancellationException) {
            Log.e(TAG, "Leaf diagnosis timed out", e)
            throw IllegalStateException("Leaf analysis timed out after 60 seconds. Please check your connection and try again.")
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "Leaf diagnosis failed", e)
            throw IllegalStateException(
                e.localizedMessage ?: "Unable to complete AI leaf analysis right now."
            )
        }
    }

    fun streamExpertReply(
        priorHistory: List<ChatMessage>,
        userQuestion: String,
        attachedImage: Bitmap?,
        preferredLanguage: String
    ): Flow<String> = flow {
        try {
            // Convert up to last 10 messages into Gemini Content history
            val geminiHistory: List<Content> = priorHistory
                .takeLast(10)
                .filter { it.text.isNotBlank() }
                .map { msg ->
                    val mappedRole = if (msg.role == "user") "user" else "model"
                    content(mappedRole) { text(msg.text) }
                }

            val chat = expertModel.startChat(history = geminiHistory)
            val promptWithLanguageHint = buildString {
                append("[Preferred response language: ")
                append(preferredLanguage)
                append(" (or match user's question language if they wrote in Hindi/English)]\n")
                append(userQuestion)
            }

            val messageContent = content("user") {
                if (attachedImage != null) {
                    image(attachedImage)
                }
                text(promptWithLanguageHint)
            }

            val accumulated = StringBuilder()
            withTimeout(AI_TIMEOUT_MS) {
                chat.sendMessageStream(messageContent).collect { chunk ->
                    val piece = chunk.text.orEmpty()
                    if (piece.isNotEmpty()) {
                        accumulated.append(piece)
                        emit(accumulated.toString())
                    }
                }
            }

            if (accumulated.isBlank()) {
                throw IllegalStateException("AI Crop Expert returned an empty response.")
            }
        } catch (e: TimeoutCancellationException) {
            Log.e(TAG, "AI Crop Expert timed out", e)
            throw IllegalStateException("AI Crop Expert timed out after 60 seconds. Please try again.")
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "AI Crop Expert failed", e)
            throw IllegalStateException(
                e.localizedMessage ?: "AI Crop Expert is currently unavailable."
            )
        }
    }

    private fun JSONObject.optStringArray(key: String): List<String> {
        val arr: JSONArray = optJSONArray(key) ?: return emptyList()
        return (0 until arr.length()).mapNotNull { idx ->
            arr.optString(idx).takeIf { it.isNotBlank() }
        }
    }
}
