package com.demo.app

class HomeFragment {
    var title: String = "Home"
        private set

    private val promotionalBanners = listOf(
        "Banner 1: Special Offer!",
        "Banner 2: New Arrivals!",
        "Banner 3: Limited Time Sale!"
    )

    fun onViewCreated() {
        println("Home fragment created")
        displayPromotionalBanners()
    }

    private fun displayPromotionalBanners() {
        println("Displaying promotional banners:")
        promotionalBanners.forEach { banner ->
            println(banner)
        }
    }
}