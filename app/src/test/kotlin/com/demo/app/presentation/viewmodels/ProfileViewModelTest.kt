package com.demo.app.presentation.viewmodels

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.BeforeTest
import com.demo.app.domain.usecases.GetUserProfileUseCase
import com.demo.app.domain.models.UserProfile

class ProfileViewModelTest {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var getUserProfileUseCase: GetUserProfileUseCase

    @BeforeTest
    fun setup() {
        getUserProfileUseCase = FakeGetUserProfileUseCase()
        viewModel = ProfileViewModel(getUserProfileUseCase)
    }

    @Test
    fun `test loading state`() {
        viewModel.loadUserProfile()
        assertTrue(viewModel.uiState is ProfileViewModel.UiState.Loading)
    }

    @Test
    fun `test success state with user profile`() {
        viewModel.loadUserProfile()
        val uiState = viewModel.uiState
        assertTrue(uiState is ProfileViewModel.UiState.Success)
        val successState = uiState as ProfileViewModel.UiState.Success
        assertEquals(1, successState.items.size)
        assertEquals("John Doe", successState.items[0].name)
    }

    @Test
    fun `test empty state`() {
        getUserProfileUseCase = FakeGetUserProfileUseCase(emptyList())
        viewModel = ProfileViewModel(getUserProfileUseCase)
        viewModel.loadUserProfile()
        assertTrue(viewModel.uiState is ProfileViewModel.UiState.Empty)
    }

    // Fake UseCase for testing
    private class FakeGetUserProfileUseCase(private val profiles: List<UserProfile> = listOf(UserProfile("John Doe"))) : GetUserProfileUseCase {
        override fun invoke(): List<UserProfile> = profiles
    }
}