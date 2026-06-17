package com.demo.app.presentation.viewmodels

import com.demo.app.domain.usecases.SearchItemsUseCase

class HomeViewModel(private val searchItemsUseCase: SearchItemsUseCase) {

    private var _uiState: UiState<String> = UiState.Empty
    val uiState: UiState<String>
        get() = _uiState

    fun search(query: String) {
        _uiState = UiState.Loading
        val results = searchItemsUseCase.execute(query) { item, q -> item.contains(q, ignoreCase = true) }
        _uiState = if (results.isEmpty()) {
            UiState.Empty
        } else {
            UiState.Success(results)
        }
    }

    sealed class UiState<out T> {
        object Loading : UiState<Nothing>()
        data class Success<T>(val items: List<T>) : UiState<T>()
        object Empty : UiState<Nothing>()
    }
}
