package com.example.data.model

data class DiagnosisAssessment(
    val id: Long = 0L,
    val cropName: String,
    val cropCategory: String,
    val imageFilePath: String,
    val assessmentStatus: String,
    val possibleCondition: String,
    val imageClarityNote: String,
    val observedSymptoms: List<String>,
    val possibleCauses: List<String>,
    val preventionGuidance: List<String>,
    val recommendedNextSteps: List<String>,
    val limitationsDisclaimer: String,
    val timestamp: Long = System.currentTimeMillis()
)
