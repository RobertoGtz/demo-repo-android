package com.demo.app.data.repositories

data class UserProfile(val id: String, val name: String, val email: String)

interface UserProfileRepository {
    fun getUserProfile(userId: String): UserProfile?
    fun updateUserProfile(userProfile: UserProfile): Boolean
    fun deleteUserProfile(userId: String): Boolean
}

class UserProfileRepositoryImpl : UserProfileRepository {
    private val userProfiles = mutableMapOf<String, UserProfile>()

    override fun getUserProfile(userId: String): UserProfile? {
        return userProfiles[userId]
    }

    override fun updateUserProfile(userProfile: UserProfile): Boolean {
        userProfiles[userProfile.id] = userProfile
        return true
    }

    override fun deleteUserProfile(userId: String): Boolean {
        return userProfiles.remove(userId) != null
    }
}