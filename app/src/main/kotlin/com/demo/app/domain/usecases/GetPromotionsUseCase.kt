package com.demo.app.domain.usecases

import com.demo.app.data.repositories.PromotionsRepository

class GetPromotionsUseCase(private val promotionsRepository: PromotionsRepository) {

    fun execute(): List<Promotion> {
        return promotionsRepository.getPromotions()
    }
}

data class Promotion(val id: String, val title: String, val description: String)