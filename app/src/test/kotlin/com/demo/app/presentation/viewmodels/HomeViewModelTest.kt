package com.demo.app.presentation.viewmodels

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.BeforeTest
import com.demo.app.domain.usecases.SearchItemsUseCase

class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var searchItemsUseCase: SearchItemsUseCase

    @BeforeTest
    fun setUp() {
        searchItemsUseCase = SearchItemsUseCase()
        homeViewModel = HomeViewModel(searchItemsUseCase)
    }

    @Test
    fun `test search returns success with items`() {
        val query = "ap"
        homeViewModel.search(query)

        assertTrue(homeViewModel.uiState is HomeViewModel.UiState.Success)
        val successState = homeViewModel.uiState as HomeViewModel.UiState.Success
        assertEquals(listOf("apple", "grape"), successState.items)
    }

    @Test
    fun `test search returns empty when no items found`() {
        val query = "xyz"
        homeViewModel.search(query)

        assertTrue(homeViewModel.uiState is HomeViewModel.UiState.Empty)
    }
}
