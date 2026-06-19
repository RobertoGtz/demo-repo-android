package com.demo.app.data.repositories

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PromotionsRepositoryTest {

    private val promotionsRepository = PromotionsRepository()

    @Test
    fun `test getPromotions returns correct data`() {
        val expectedPromotions = listOf(
            Promotion(id = 1, title = "Summer Sale", description = "Up to 50% off!"),
            Promotion(id = 2, title = "Winter Clearance", description = "Clearance sale up to 70% off!")
        )

        val promotions = promotionsRepository.getPromotions()

        assertEquals(expectedPromotions.size, promotions.size, "The number of promotions should match")
        assertTrue(promotions.containsAll(expectedPromotions), "The promotions should match the expected list")
    }

    @Test
    fun `test getPromotions returns empty list when no promotions available`() {
        val promotionsRepository = PromotionsRepository(emptyList())

        val promotions = promotionsRepository.getPromotions()

        assertTrue(promotions.isEmpty(), "The promotions list should be empty")
    }
}