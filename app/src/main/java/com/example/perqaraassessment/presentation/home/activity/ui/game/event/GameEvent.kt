package com.example.perqaraassessment.presentation.home.activity.ui.game.event

sealed class GameEvent {
    object LoadMore: GameEvent()
    object DefaultPage: GameEvent()
}