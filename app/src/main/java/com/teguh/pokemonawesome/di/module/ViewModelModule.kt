package com.teguh.pokemonawesome.di.module

import com.teguh.pokemonawesome.ui.main.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(get(),get())
    }
}