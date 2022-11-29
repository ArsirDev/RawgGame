package com.example.data.repository.local

import com.example.domain.repository.local.LocalGamesRepository
import com.example.perqaraassessment.core.util.SessionManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class LocalGamesRepositoryImpl(
    private val sessionManager: SessionManager,
    private val dispatcher: CoroutineDispatcher
): LocalGamesRepository {
    override suspend fun setFirstInstallStatus(status: Boolean) {
        return sessionManager.setFirstInstall(status)
    }

    override suspend fun getFirstInstallStatus(): Flow<Boolean> {
        return sessionManager.getFirstInstall.flowOn(dispatcher)
    }
}