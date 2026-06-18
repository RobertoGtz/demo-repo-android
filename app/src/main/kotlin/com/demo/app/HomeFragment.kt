package com.demo.app

class HomeFragment {
    var title: String = "Home"
        private set

    fun onViewCreated() {
        println("Home fragment created")
    }

    fun navigateToSettings() {
        println("Navigating to Settings screen")
    }
}