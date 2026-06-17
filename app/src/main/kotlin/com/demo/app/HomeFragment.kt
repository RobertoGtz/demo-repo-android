package com.demo.app

class HomeFragment {
    var title: String = "Home"
        private set

    fun onViewCreated() {
        println("Home fragment created")
        setupSettingsIcon()
    }

    private fun setupSettingsIcon() {
        println("Settings icon tapped")
        navigateToSettings()
    }

    private fun navigateToSettings() {
        println("Navigating to SettingsFragment")
        // Logic to navigate to SettingsFragment
        // Assuming there's a method or mechanism to handle the actual navigation
    }
}
