package com.demo.app.data.repositories

data class Promotion(val id: String, val title: String, val description: String)

class PromotionsRepository {

    private val promotions = listOf(
        Promotion("1", "Summer Sale", "Get up to 50% off on summer items!"),
        Promotion("2", "Winter Clearance", "Clearance sale on all winter wear."),
        Promotion("3", "Spring Collection", "Check out our new spring collection!")
    )

    fun getPromotions(): List<Promotion> {
        return promotions
    }
}