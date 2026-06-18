package com.demo.app

class HomeFragment {
    var title: String = "Home"
        private set

    fun onViewCreated() {
        println("Home fragment created")
        setupProfileIconClickListener()
    }

    private fun setupProfileIconClickListener() {
        println("Profile icon click listener set")
        navigateToProfileFragment()
    }

    private fun navigateToProfileFragment() {
        println("Navigating to ProfileFragment")
        // Logic to navigate to ProfileFragment
    }
}