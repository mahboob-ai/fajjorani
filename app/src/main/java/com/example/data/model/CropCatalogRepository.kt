package com.example.data.model

object CropCatalogRepository {
    val allCrops: List<CropInfo> by lazy {
        vegetableAndFruitCrops + cerealPulseAndCashCrops
    }

    fun cropsByCategory(category: CropCategory?): List<CropInfo> {
        return if (category == null) allCrops else allCrops.filter { it.category == category }
    }

    fun searchCrops(query: String, category: CropCategory? = null): List<CropInfo> {
        val base = cropsByCategory(category)
        val trimmed = query.trim()
        if (trimmed.isEmpty()) return base
        return base.filter { crop ->
            crop.name.contains(trimmed, ignoreCase = true) ||
                crop.scientificName.contains(trimmed, ignoreCase = true) ||
                crop.category.displayName.contains(trimmed, ignoreCase = true) ||
                crop.commonDiseases.any { it.name.contains(trimmed, ignoreCase = true) }
        }
    }

    fun findByName(name: String): CropInfo? {
        return allCrops.firstOrNull { it.name.equals(name, ignoreCase = true) }
    }
}
