package com.example.splashscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.local.LocalGamesRepository
import com.example.splashscreen.state.SplashScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val localGamesRepository: LocalGamesRepository
): ViewModel() {

    private val _state = MutableStateFlow(SplashScreenState())

    val state get() = _state.asStateFlow()

    init {
        getStatus()
    }

    private fun getStatus() = viewModelScope.launch {
        localGamesRepository.getFirstInstallStatus().onEach { result ->
           delay(5000)
            _state.value = state.value.copy(
                isLoading = false,
                status = result
            )
        }
    }
}