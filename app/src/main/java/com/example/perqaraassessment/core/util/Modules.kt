package com.example.perqaraassessment.core.util

import com.example.data.repository.local.LocalGamesRepositoryImpl
import com.example.domain.repository.local.LocalGamesRepository
import org.koin.dsl.module

val local = module {
    single<LocalGamesRepository> { LocalGamesRepositoryImpl(sessionManager = get(), dispatcher = get()) }
    single { SessionManager(context = get()) }
}
