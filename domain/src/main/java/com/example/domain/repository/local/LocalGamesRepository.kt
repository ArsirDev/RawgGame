package com.example.domain.repository.local

import kotlinx.coroutines.flow.Flow

interface LocalGamesRepository {
    suspend fun setFirstInstallStatus(status: Boolean)
    suspend fun getFirstInstallStatus(): Flow<Boolean>
}