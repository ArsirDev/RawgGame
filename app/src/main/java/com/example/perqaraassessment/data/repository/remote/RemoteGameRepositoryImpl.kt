package com.example.perqaraassessment.data.repository.remote
import com.example.perqaraassessment.data.local.database.GamesDatabase
import com.example.perqaraassessment.data.remote.api.ApiInterface
import com.example.perqaraassessment.data.remote.dto.GameResponse
import com.example.perqaraassessment.domain.repository.remote.RemoteGameRepository
import com.example.perqaraassessment.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteGameRepositoryImpl(
    private val service: ApiInterface,
    private val gamesDatabase: GamesDatabase
): RemoteGameRepository {
    override fun getGame(page: Int): Flow<Result<GameResponse>> = flow {
        emit(Result.Loading())

        val result = service.getGame(page)

        if (result.isSuccessful) {
            result.body()?.let { response ->
                emit(Result.Success(response))
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }

//    override fun getSearchGame(page: Int, search: String?): Flow<Result<GameResponse>> {
//
//    }
}