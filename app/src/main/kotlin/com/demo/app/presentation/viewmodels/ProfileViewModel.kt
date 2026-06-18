package com.demo.app.presentation.viewmodels

import com.demo.app.domain.usecases.GetUserProfileUseCase

class ProfileViewModel(private val getUserProfileUseCase: GetUserProfileUseCase) {

    private var _uiState: UiState<UserProfile> = UiState.Empty
    val uiState: UiState<UserProfile>
        get() = _uiState

    fun loadUserProfile() {
        _uiState = UiState.Loading
        val userProfile = getUserProfileUseCase.execute()
        _uiState = if (userProfile.isNotEmpty()) {
            UiState.Success(userProfile)
        } else {
            UiState.Empty
        }
    }

    sealed class UiState<out T> {
        object Loading : UiState<Nothing>()
        data class Success<T>(val items: List<T>) : UiState<T>()
        object Empty : UiState<Nothing>()
    }
}

data class UserProfile(val id: String, val name: String, val email: String)