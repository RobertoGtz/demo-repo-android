package com.demo.app

import com.demo.app.presentation.viewmodels.HomeViewModel
import com.demo.app.domain.usecases.SearchItemsUseCase

class HomeFragment {
    var title: String = "Home"
        private set

    private val searchItemsUseCase = SearchItemsUseCase()
    private val viewModel = HomeViewModel(searchItemsUseCase)

    fun onViewCreated() {
        println("Home fragment created")
        setupSearchBar()
    }

    private fun setupSearchBar() {
        println("Search bar setup")
        // Simulate user input for search
        val query = "ap"
        viewModel.search(query)
        displayResults((viewModel.uiState as? HomeViewModel.UiState.Success)?.items ?: emptyList())
    }

    private fun displayResults(results: List<String>) {
        if (results.isEmpty()) {
            println("No results found")
        } else {
            println("Displaying results: ${results.joinToString(", ")}")
        }
    }
}
