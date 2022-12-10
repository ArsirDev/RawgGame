package com.example.perqaraassessment.presentation.home.activity.ui.game.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perqaraassessment.domain.repository.remote.RemoteGameRepository
import com.example.perqaraassessment.presentation.home.activity.ui.game.event.GameEvent
import com.example.perqaraassessment.presentation.home.activity.ui.game.state.GameState
import com.example.perqaraassessment.util.Result
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class GameViewModel(
    private val repository: RemoteGameRepository
): ViewModel() {

    private val _state = MutableStateFlow(GameState())

    val state get() = _state.asStateFlow()

    private var job: Job? = null

    fun onEvent(event: GameEvent) {
        when(event) {
            GameEvent.DefaultPage -> {
                getGames(page = 1)
            }
            GameEvent.LoadMore -> {
                if (state.value.allowLoadNext) {
                    getGames(state.value.page + 1)
                }
            }
        }
    }

    private fun getGames(page: Int) {
        job?.cancel()
        job = viewModelScope.launch {
            delay(1000)
            repository.getGame(page).onEach { result ->
                when(result) {
                    is Result.Loading -> {
                        _state.value = state.value.copy(isLoading = true)
                    }
                    is Result.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            allowLoadNext = (result.data?.results ?: emptyList()).isNotEmpty(),
                            page = page,
                            game = if (page == 1) {
                                result.data?.results ?: emptyList()
                            } else {
                                state.value.game + (result.data?.results ?: emptyList())
                            }
                        )
                    }
                    is Result.Error -> {
                        _state.value = state.value.copy(isLoading = false)
                    }
                }
            }.launchIn(this + IO)
        }
    }
}