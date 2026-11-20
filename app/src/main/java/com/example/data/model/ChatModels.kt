package com.example.data.model

data class ChatMessage(
    val id: Long = 0L,
    val role: String, // "user" or "model"
    val text: String,
    val imageFilePath: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)

data class EducationalFaq(
    val question: String,
    val answer: String,
    val categoryTag: String
)

object ExpertKnowledgeFallback {
    val suggestedQuestions = listOf(
        "Why are my tomato leaves turning yellow?",
        "What causes brown spots on rice leaves?",
        "How can I prevent fungal diseases?",
        "Why are my potato leaves drying?",
        "How can I improve crop health?"
    )

    val educationalFaqs = listOf(
        EducationalFaq(
            question = "Why are my tomato leaves turning yellow?",
            categoryTag = "Tomato Care",
            answer = "Yellowing tomato leaves commonly stem from early blight (concentric brown spots on lower leaves), over-watering/poor drainage, nitrogen or magnesium deficiency, or sap-sucking pests like whiteflies. Check leaf undersides, water at the soil base in the morning, and remove badly affected lower leaves. Consult your local Krishi Vigyan Kendra (KVK) if symptoms spread rapidly."
        ),
        EducationalFaq(
            question = "What causes brown spots on rice leaves?",
            categoryTag = "Rice Care",
            answer = "Brown spots on rice leaves are often associated with Rice Brown Spot (oval dark brown spots, frequently linked to potassium or soil nutrient stress) or Rice Blast (diamond-shaped lesions with gray centers). Inspect whether spots are diamond-shaped or oval, avoid excessive nitrogen, and seek local agronomist verification before treatment."
        ),
        EducationalFaq(
            question = "How can I prevent fungal diseases?",
            categoryTag = "General Prevention",
            answer = "Key preventive practices include: (1) watering at the root zone in the early morning instead of overhead wetting at dusk, (2) maintaining proper plant spacing for airflow, (3) practicing crop rotation across seasons, (4) using clean certified seed, and (5) removing heavily infected crop debris promptly."
        ),
        EducationalFaq(
            question = "Why are my potato leaves drying?",
            categoryTag = "Potato Care",
            answer = "Drying potato leaves may be caused by Late Blight (dark water-soaked blotches turning brown/black in cool humid weather), Early Blight (dry ringed spots), moisture stress, or bacterial wilt. Check stems and leaf undersides early in the morning and verify with a local agricultural specialist before chemical intervention."
        ),
        EducationalFaq(
            question = "How can I improve crop health?",
            categoryTag = "Soil & Crop Care",
            answer = "Healthy crops start with well-drained organic-rich soil, periodic soil testing for balanced NPK and micronutrients, mulching to protect moisture, timely weeding, and weekly leaf scouting so early stress signs are caught before major yield loss."
        )
    )
}
