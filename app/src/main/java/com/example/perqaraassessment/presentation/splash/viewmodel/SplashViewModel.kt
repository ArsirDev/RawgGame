package com.example.perqaraassessment.presentation.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perqaraassessment.domain.repository.local.LocalGameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class SplashViewModel(
    private val repository: LocalGameRepository
): ViewModel() {

    private val _state = MutableSharedFlow<Boolean>()

    val state: SharedFlow<Boolean> get() = _state.asSharedFlow()

    private val _isLoading = MutableStateFlow(false)

    val isLoading get() = _isLoading.asStateFlow()

    private fun onLoading() = flow<Boolean> {
        delay(5000)
        _isLoading.value = false
    }.launchIn(viewModelScope + Dispatchers.IO)

    private fun getFirstInstall() = viewModelScope.launch {
        repository.getFirstInstallStatus().collectLatest { status ->
            _state.emit(status)
        }
    }

    init {
        onLoading()
        getFirstInstall()
    }
}