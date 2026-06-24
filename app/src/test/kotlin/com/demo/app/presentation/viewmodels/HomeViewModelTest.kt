package com.demo.app.presentation.viewmodels

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import com.demo.app.domain.usecases.GetPromotionalBannersUseCase
import com.demo.app.presentation.viewmodels.HomeViewModel.UiState

class HomeViewModelTest {

    private val promotionalBanners = listOf("Banner1", "Banner2", "Banner3")
    private val getPromotionalBannersUseCase = object : GetPromotionalBannersUseCase {
        override fun execute(): List<String> {
            return promotionalBanners
        }
    }

    private val homeViewModel = HomeViewModel(getPromotionalBannersUseCase)

    @Test
    fun `test initial state is loading`() {
        val initialState = homeViewModel.uiState
        assertTrue(initialState is UiState.Loading)
    }

    @Test
    fun `test success state with promotional banners`() {
        homeViewModel.loadPromotionalBanners()
        val successState = homeViewModel.uiState as UiState.Success
        assertEquals(promotionalBanners, successState.items)
    }

    @Test
    fun `test empty state when no promotional banners`() {
        val emptyUseCase = object : GetPromotionalBannersUseCase {
            override fun execute(): List<String> {
                return emptyList()
            }
        }
        val emptyViewModel = HomeViewModel(emptyUseCase)
        emptyViewModel.loadPromotionalBanners()
        assertTrue(emptyViewModel.uiState is UiState.Empty)
    }
}