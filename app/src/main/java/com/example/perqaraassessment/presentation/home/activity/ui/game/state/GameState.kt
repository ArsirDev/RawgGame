package com.example.perqaraassessment.presentation.home.activity.ui.game.state

import com.example.perqaraassessment.domain.model.Game

data class GameState(
    val isLoading: Boolean = false,
    val game: List<Game> = emptyList(),
    val allowLoadNext: Boolean = false,
    val page: Int = 1,
)
