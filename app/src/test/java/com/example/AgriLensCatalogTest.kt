package com.example

import com.example.data.model.CropCatalogRepository
import com.example.data.model.CropCategory
import com.example.data.model.ExpertKnowledgeFallback
import com.example.data.model.PresentationDeck
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class AgriLensCatalogTest {

    @Test
    fun cropCatalog_containsAll42CropsAcross5Categories() {
        assertEquals(42, CropCatalogRepository.allCrops.size)
        assertEquals(16, CropCatalogRepository.cropsByCategory(CropCategory.VEGETABLES).size)
        assertEquals(10, CropCatalogRepository.cropsByCategory(CropCategory.FRUITS).size)
        assertEquals(5, CropCatalogRepository.cropsByCategory(CropCategory.CEREALS).size)
        assertEquals(6, CropCatalogRepository.cropsByCategory(CropCategory.PULSES).size)
        assertEquals(5, CropCatalogRepository.cropsByCategory(CropCategory.CASH_CROPS).size)
    }

    @Test
    fun cropCatalog_everyCropHasCompleteEducationalFields() {
        CropCatalogRepository.allCrops.forEach { crop ->
            assertTrue("Missing name for ${crop.id}", crop.name.isNotBlank())
            assertTrue("Missing conditions for ${crop.id}", crop.suitableGrowingConditions.isNotBlank())
            assertTrue("Missing symptoms for ${crop.id}", crop.commonVisibleSymptoms.isNotEmpty())
            assertTrue("Missing diseases for ${crop.id}", crop.commonDiseases.isNotEmpty())
            assertTrue("Missing prevention for ${crop.id}", crop.generalPreventionMethods.isNotEmpty())
            assertTrue("Missing care tips for ${crop.id}", crop.cropCareTips.isNotEmpty())
        }
    }

    @Test
    fun cropCatalog_searchFindsByCropAndDisease() {
        assertNotNull(CropCatalogRepository.findByName("Tomato"))
        val blastMatches = CropCatalogRepository.searchCrops("Blast", CropCategory.CEREALS)
        assertTrue(blastMatches.any { it.id == "rice" })
    }

    @Test
    fun presentationDeck_hasAll8SlidesWithSpeakerNotes() {
        assertEquals(8, PresentationDeck.slides.size)
        PresentationDeck.slides.forEachIndexed { idx, slide ->
            assertEquals(idx + 1, slide.slideNumber)
            assertTrue(slide.keyPoints.isNotEmpty())
            assertTrue(slide.speakerNotes.isNotBlank())
        }
    }

    @Test
    fun expertFallback_hasAll5SuggestedQuestionsAndFaqs() {
        assertEquals(5, ExpertKnowledgeFallback.suggestedQuestions.size)
        assertEquals(5, ExpertKnowledgeFallback.educationalFaqs.size)
    }
}
