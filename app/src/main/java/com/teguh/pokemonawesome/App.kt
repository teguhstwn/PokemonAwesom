package com.teguh.pokemonawesome

import android.app.Application
import com.teguh.pokemonawesome.di.module.appModule
import com.teguh.pokemonawesome.di.module.repoModule
import com.teguh.pokemonawesome.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}