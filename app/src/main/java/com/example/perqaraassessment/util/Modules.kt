package com.example.perqaraassessment.util

import com.example.perqaraassessment.data.local.database.GamesDatabase
import com.example.perqaraassessment.data.remote.api.ApiInterface
import com.example.perqaraassessment.data.repository.local.LocalGameRepositoryImpl
import com.example.perqaraassessment.data.repository.remote.RemoteGameRepositoryImpl
import com.example.perqaraassessment.domain.repository.local.LocalGameRepository
import com.example.perqaraassessment.domain.repository.remote.RemoteGameRepository
import com.example.perqaraassessment.presentation.home.activity.ui.game.adapter.GamesAdapter
import com.example.perqaraassessment.presentation.home.activity.ui.game.viewmodel.GameViewModel
import com.example.perqaraassessment.presentation.slider.adapter.SliderAdapter
import com.example.perqaraassessment.presentation.slider.viewmodel.SliderViewModel
import com.example.perqaraassessment.presentation.splash.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val local = module {
    single<LocalGameRepository> { LocalGameRepositoryImpl(sessionManager = get()) }
    single { ServiceGenerate.createService(serviceClass = ApiInterface::class.java) }
    single { GamesDatabase.invoke(context = get()) }
    single { SessionManager(context = get()) }
}

val remote = module {
    single<RemoteGameRepository> { RemoteGameRepositoryImpl(service = get(), gamesDatabase = get()) }
}

val splash = module {
    viewModel { SplashViewModel(repository = get()) }
}

val slider = module {
    viewModel { SliderViewModel(repository = get()) }
    factory { SliderAdapter() }
}

val game = module {
    viewModel { GameViewModel(repository = get()) }
    factory { GamesAdapter() }
}

