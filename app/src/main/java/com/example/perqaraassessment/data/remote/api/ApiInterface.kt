package com.example.perqaraassessment.data.remote.api

import com.example.perqaraassessment.data.remote.dto.GameResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/api/games")
    suspend fun getGames(
        @Query("page") page: Int,
    ): Response<GameResponse>

    @GET("/api/games")
    suspend fun getGameSearch(
        @Query("page") page: Int,
        @Query("search") search: String?,
    ): Response<GameResponse>

    @GET("/api/games/{id}")
    suspend fun getGame(
        @Path("id") id: Int,
    ): Response<GameResponse>
}