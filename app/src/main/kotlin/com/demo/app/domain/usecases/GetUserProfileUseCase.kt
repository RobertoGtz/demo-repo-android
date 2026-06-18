package com.demo.app.domain.usecases

import com.demo.app.data.repositories.UserRepository
import com.demo.app.domain.models.UserProfile

class GetUserProfileUseCase(private val userRepository: UserRepository) {

    fun execute(userId: String): List<UserProfile> {
        return userRepository.getUserProfile(userId)
    }
}