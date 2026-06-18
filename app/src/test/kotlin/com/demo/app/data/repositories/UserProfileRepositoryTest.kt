package com.demo.app.data.repositories

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertNotNull

class UserProfileRepositoryTest {

    private val userProfileRepository = UserProfileRepository()

    @Test
    fun `test getUserProfile returns valid user profile`() {
        val userId = "123"
        val userProfile = userProfileRepository.getUserProfile(userId)

        assertNotNull(userProfile, "User profile should not be null")
        assertEquals(userId, userProfile.id, "User ID should match")
        assertTrue(userProfile.name.isNotEmpty(), "User name should not be empty")
    }

    @Test
    fun `test getUserProfile returns empty profile for invalid user`() {
        val invalidUserId = "999"
        val userProfile = userProfileRepository.getUserProfile(invalidUserId)

        assertNotNull(userProfile, "User profile should not be null")
        assertEquals("", userProfile.name, "User name should be empty for invalid user")
    }

    @Test
    fun `test updateUserProfile updates the user profile`() {
        val userId = "123"
        val updatedName = "New Name"
        val userProfile = userProfileRepository.getUserProfile(userId)

        userProfileRepository.updateUserProfile(userId, updatedName)

        val updatedUserProfile = userProfileRepository.getUserProfile(userId)
        assertEquals(updatedName, updatedUserProfile.name, "User name should be updated")
    }
}