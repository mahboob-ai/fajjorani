package com.example.data.local

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.ChatMessage
import com.example.data.model.DiagnosisAssessment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray

@Entity(tableName = "diagnosis_records")
data class DiagnosisRecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val cropName: String,
    val cropCategory: String,
    val imageFilePath: String,
    val assessmentStatus: String,
    val possibleCondition: String,
    val imageClarityNote: String,
    val observedSymptomsJson: String,
    val possibleCausesJson: String,
    val preventionGuidanceJson: String,
    val recommendedNextStepsJson: String,
    val limitationsDisclaimer: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "chat_messages")
data class ChatMessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val role: String,
    val text: String,
    val imageFilePath: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)

@Dao
interface DiagnosisDao {
    @Query("SELECT * FROM diagnosis_records ORDER BY timestamp DESC")
    fun getAllRecords(): Flow<List<DiagnosisRecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(entity: DiagnosisRecordEntity): Long

    @Query("DELETE FROM diagnosis_records WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM diagnosis_records")
    suspend fun clearAll()
}

@Dao
interface ChatDao {
    @Query("SELECT * FROM chat_messages ORDER BY timestamp ASC, id ASC")
    fun getAllMessages(): Flow<List<ChatMessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(entity: ChatMessageEntity): Long

    @Query("DELETE FROM chat_messages WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM chat_messages")
    suspend fun clearAll()
}

@Database(
    entities = [DiagnosisRecordEntity::class, ChatMessageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AgriLensDatabase : RoomDatabase() {
    abstract fun diagnosisDao(): DiagnosisDao
    abstract fun chatDao(): ChatDao

    companion object {
        @Volatile
        private var INSTANCE: AgriLensDatabase? = null

        fun getInstance(context: Context): AgriLensDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AgriLensDatabase::class.java,
                    "agrilens_local_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}

class HistoryRepository(
    private val diagnosisDao: DiagnosisDao,
    private val chatDao: ChatDao
) {
    val diagnosisHistory: Flow<List<DiagnosisAssessment>> = diagnosisDao.getAllRecords().map { list ->
        list.map { it.toDomain() }
    }

    val chatHistory: Flow<List<ChatMessage>> = chatDao.getAllMessages().map { list ->
        list.map { it.toDomain() }
    }

    suspend fun saveDiagnosis(assessment: DiagnosisAssessment): Long {
        return diagnosisDao.insertRecord(assessment.toEntity())
    }

    suspend fun deleteDiagnosis(id: Long) {
        diagnosisDao.deleteById(id)
    }

    suspend fun clearAllDiagnoses() {
        diagnosisDao.clearAll()
    }

    suspend fun saveChatMessage(message: ChatMessage): Long {
        return chatDao.insertMessage(message.toEntity())
    }

    suspend fun deleteChatMessage(id: Long) {
        chatDao.deleteById(id)
    }

    suspend fun clearAllChat() {
        chatDao.clearAll()
    }

    private fun DiagnosisRecordEntity.toDomain(): DiagnosisAssessment {
        return DiagnosisAssessment(
            id = id,
            cropName = cropName,
            cropCategory = cropCategory,
            imageFilePath = imageFilePath,
            assessmentStatus = assessmentStatus,
            possibleCondition = possibleCondition,
            imageClarityNote = imageClarityNote,
            observedSymptoms = jsonToList(observedSymptomsJson),
            possibleCauses = jsonToList(possibleCausesJson),
            preventionGuidance = jsonToList(preventionGuidanceJson),
            recommendedNextSteps = jsonToList(recommendedNextStepsJson),
            limitationsDisclaimer = limitationsDisclaimer,
            timestamp = timestamp
        )
    }

    private fun DiagnosisAssessment.toEntity(): DiagnosisRecordEntity {
        return DiagnosisRecordEntity(
            id = id,
            cropName = cropName,
            cropCategory = cropCategory,
            imageFilePath = imageFilePath,
            assessmentStatus = assessmentStatus,
            possibleCondition = possibleCondition,
            imageClarityNote = imageClarityNote,
            observedSymptomsJson = listToJson(observedSymptoms),
            possibleCausesJson = listToJson(possibleCauses),
            preventionGuidanceJson = listToJson(preventionGuidance),
            recommendedNextStepsJson = listToJson(recommendedNextSteps),
            limitationsDisclaimer = limitationsDisclaimer,
            timestamp = timestamp
        )
    }

    private fun ChatMessageEntity.toDomain(): ChatMessage {
        return ChatMessage(
            id = id,
            role = role,
            text = text,
            imageFilePath = imageFilePath,
            timestamp = timestamp
        )
    }

    private fun ChatMessage.toEntity(): ChatMessageEntity {
        return ChatMessageEntity(
            id = id,
            role = role,
            text = text,
            imageFilePath = imageFilePath,
            timestamp = timestamp
        )
    }

    private fun listToJson(items: List<String>): String {
        val array = JSONArray()
        items.forEach { array.put(it) }
        return array.toString()
    }

    private fun jsonToList(raw: String): List<String> {
        return try {
            val array = JSONArray(raw)
            (0 until array.length()).mapNotNull { idx ->
                array.optString(idx).takeIf { it.isNotBlank() }
            }
        } catch (_: Exception) {
            emptyList()
        }
    }
}
