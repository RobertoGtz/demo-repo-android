package com.demo.app.domain.usecases

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetPromotionsUseCaseTest {

    private val promotionsRepository = FakePromotionsRepository()
    private val getPromotionsUseCase = GetPromotionsUseCase(promotionsRepository)

    @Test
    fun `test get promotions returns non-empty list`() {
        val promotions = getPromotionsUseCase.execute()
        assertTrue(promotions.isNotEmpty(), "Promotions list should not be empty")
    }

    @Test
    fun `test get promotions returns correct promotions`() {
        val expectedPromotions = listOf("Promo1", "Promo2", "Promo3")
        promotionsRepository.setPromotions(expectedPromotions)

        val promotions = getPromotionsUseCase.execute()
        assertEquals(expectedPromotions, promotions, "Promotions list should match expected promotions")
    }

    @Test
    fun `test get promotions returns empty list when no promotions available`() {
        promotionsRepository.setPromotions(emptyList())

        val promotions = getPromotionsUseCase.execute()
        assertTrue(promotions.isEmpty(), "Promotions list should be empty when no promotions are available")
    }

    // Fake repository for testing purposes
    private class FakePromotionsRepository : PromotionsRepository {
        private var promotions = listOf<String>()

        fun setPromotions(promotions: List<String>) {
            this.promotions = promotions
        }

        override fun getPromotions(): List<String> {
            return promotions
        }
    }
}