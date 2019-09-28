package com.david.pokeapp

import android.app.Application
import com.david.data.koin.apiModule
import com.david.data.koin.dataModule
import com.david.domain.koin.domainModule
import com.david.pokeapp.koin.appModule
import org.koin.android.ext.android.startKoin

class PokeAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, domainModule, dataModule, apiModule))
    }
}