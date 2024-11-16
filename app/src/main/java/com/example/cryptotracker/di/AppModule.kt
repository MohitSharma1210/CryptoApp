package com.example.cryptotracker.di

import com.example.cryptotracker.core.data.networking.HttpClientFactory
import com.example.cryptotracker.crypto.data.networking.RemoteCoinDataSource
import com.example.cryptotracker.crypto.domain.CoinDataSource
import com.example.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClientFactory.create(CIO.create())
    }

// Use single when you need to define a custom instantiation function or when your class has dependencies that need to be passed explicitly.
// Use singleOf for simpler, cleaner declarations where Koin can resolve dependencies automatically from the class's constructor.


    // We can create like this also for RemoteCoinDataSource
//    single {
//        RemoteCoinDataSource(get())
//    }

    // We can use singleOf also
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()
    viewModelOf(::CoinListViewModel)

}