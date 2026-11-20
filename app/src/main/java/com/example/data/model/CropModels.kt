package com.example.data.model

enum class CropCategory(
    val displayName: String,
    val shortTitle: String,
    val description: String
) {
    VEGETABLES(
        displayName = "Vegetables",
        shortTitle = "Vegetables",
        description = "Leafy greens, solanaceous, cucurbits, and root/bulb crops"
    ),
    FRUITS(
        displayName = "Fruits",
        shortTitle = "Fruits",
        description = "Orchard, tropical, citrus, and vine fruit crops"
    ),
    CEREALS(
        displayName = "Cereals",
        shortTitle = "Cereals",
        description = "Staple grain crops vital to global food security"
    ),
    PULSES(
        displayName = "Pulses",
        shortTitle = "Pulses",
        description = "Nitrogen-fixing leguminous crops rich in plant protein"
    ),
    CASH_CROPS(
        displayName = "Cash & Other Crops",
        shortTitle = "Cash Crops",
        description = "Fiber, sugar, and oilseed commercial crops"
    )
}

data class CropDiseaseInfo(
    val name: String,
    val causalType: String,
    val description: String
)

data class CropInfo(
    val id: String,
    val name: String,
    val category: CropCategory,
    val scientificName: String,
    val suitableGrowingConditions: String,
    val commonVisibleSymptoms: List<String>,
    val commonDiseases: List<CropDiseaseInfo>,
    val generalPreventionMethods: List<String>,
    val cropCareTips: List<String>
)
