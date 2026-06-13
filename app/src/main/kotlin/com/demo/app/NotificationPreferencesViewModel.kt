package com.demo.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotificationPreferencesViewModel(
    private val repository: NotificationPreferencesRepository
) : ViewModel() {

    private val _notificationEnabled = MutableLiveData<Boolean>()
    val notificationEnabled: LiveData<Boolean> get() = _notificationEnabled

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    init {
        loadPreferences()
    }

    private fun loadPreferences() {
        viewModelScope.launch {
            try {
                val isEnabled = repository.getNotificationEnabled()
                _notificationEnabled.value = isEnabled
            } catch (e: Exception) {
                _error.value = "Failed to load preferences"
            }
        }
    }

    fun setNotificationEnabled(enabled: Boolean) {
        viewModelScope.launch {
            try {
                repository.setNotificationEnabled(enabled)
                _notificationEnabled.value = enabled
            } catch (e: Exception) {
                _error.value = "Failed to update preferences"
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}

interface NotificationPreferencesRepository {
    suspend fun getNotificationEnabled(): Boolean
    suspend fun setNotificationEnabled(enabled: Boolean)
}