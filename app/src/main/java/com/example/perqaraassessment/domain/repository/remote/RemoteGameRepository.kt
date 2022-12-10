package com.example.perqaraassessment.domain.repository.remote

import com.example.perqaraassessment.data.remote.dto.GameResponse
import com.example.perqaraassessment.domain.model.Game
import com.example.perqaraassessment.util.Result
import kotlinx.coroutines.flow.Flow

interface RemoteGameRepository {
    fun getGame(page: Int): Flow<Result<GameResponse>>

//    fun getSearchGame(page: Int, search: String?): Flow<Result<GameResponse>>
}