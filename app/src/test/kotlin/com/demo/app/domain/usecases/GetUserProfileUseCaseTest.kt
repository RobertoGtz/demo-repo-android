package com.demo.app.domain.usecases

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.BeforeTest
import com.demo.app.data.repositories.UserRepository

class GetUserProfileUseCaseTest {

    private lateinit var getUserProfileUseCase: GetUserProfileUseCase
    private lateinit var userRepository: UserRepository

    @BeforeTest
    fun setUp() {
        userRepository = FakeUserRepository()
        getUserProfileUseCase = GetUserProfileUseCase(userRepository)
    }

    @Test
    fun `test getUserProfile returns user profile successfully`() {
        val userProfile = getUserProfileUseCase.execute()

        assertTrue(userProfile.isNotEmpty(), "User profile should not be empty")
        assertEquals(1, userProfile.size, "User profile list should contain exactly one profile")
        assertEquals("John Doe", userProfile[0].name, "User name should be 'John Doe'")
    }

    @Test
    fun `test getUserProfile returns empty when no profile exists`() {
        userRepository = EmptyUserRepository()
        getUserProfileUseCase = GetUserProfileUseCase(userRepository)

        val userProfile = getUserProfileUseCase.execute()

        assertTrue(userProfile.isEmpty(), "User profile should be empty")
    }

    // Fake repository for testing
    private class FakeUserRepository : UserRepository {
        override fun getUserProfile(): List<UserProfile> {
            return listOf(UserProfile(name = "John Doe"))
        }
    }

    // Empty repository for testing empty case
    private class EmptyUserRepository : UserRepository {
        override fun getUserProfile(): List<UserProfile> {
            return emptyList()
        }
    }

    // Dummy UserProfile data class
    data class UserProfile(val name: String)
}