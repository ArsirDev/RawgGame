package com.example.perqaraassessment.presentation.slider.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perqaraassessment.domain.repository.local.LocalGameRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class SliderViewModel(
    private val repository: LocalGameRepository
): ViewModel() {

    private val _isLoading = MutableStateFlow(false)

    val isLoading get() = _isLoading.asStateFlow()

    private fun onLoading() = flow<Boolean> {
        delay(5000)
        _isLoading.value = false
    }

    fun setFirstInstall(
        status: Boolean
    ) = viewModelScope.launch {
        repository.setFirstInstallStatus(status)
    }

    init {
        onLoading()
    }
}