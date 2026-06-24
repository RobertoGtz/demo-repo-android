package com.demo.app.presentation.viewmodels

import com.demo.app.domain.usecases.GetPromotionalBannersUseCase

class HomeViewModel(private val getPromotionalBannersUseCase: GetPromotionalBannersUseCase) {

    sealed class UiState<out T> {
        object Loading : UiState<Nothing>()
        data class Success<T>(val items: List<T>) : UiState<T>()
        object Empty : UiState<Nothing>()
    }

    fun getPromotionalBanners(): UiState<String> {
        val banners = getPromotionalBannersUseCase.execute()
        return if (banners.isEmpty()) {
            UiState.Empty
        } else {
            UiState.Success(banners)
        }
    }
}