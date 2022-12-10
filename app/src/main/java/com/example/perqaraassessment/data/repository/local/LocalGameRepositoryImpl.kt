package com.example.perqaraassessment.data.repository.local

import com.example.perqaraassessment.domain.repository.local.LocalGameRepository
import com.example.perqaraassessment.util.SessionManager
import kotlinx.coroutines.flow.Flow

class LocalGameRepositoryImpl(
    private val sessionManager: SessionManager
): LocalGameRepository {
    override suspend fun setFirstInstallStatus(status: Boolean) {
        return sessionManager.setFirstInstall(status)
    }

    override suspend fun getFirstInstallStatus(): Flow<Boolean> {
        return sessionManager.getFirstInstall
    }
}