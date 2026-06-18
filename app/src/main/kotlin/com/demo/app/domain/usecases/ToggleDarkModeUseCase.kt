package com.demo.app.domain.usecases

class ToggleDarkModeUseCase {

    private var isDarkModeEnabled: Boolean = false

    fun execute(): Boolean {
        isDarkModeEnabled = !isDarkModeEnabled
        return isDarkModeEnabled
    }
}