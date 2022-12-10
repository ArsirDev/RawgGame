package com.example.perqaraassessment.domain.repository.local

import kotlinx.coroutines.flow.Flow

interface LocalGameRepository {

    suspend fun setFirstInstallStatus(status: Boolean)

    suspend fun getFirstInstallStatus(): Flow<Boolean>
}