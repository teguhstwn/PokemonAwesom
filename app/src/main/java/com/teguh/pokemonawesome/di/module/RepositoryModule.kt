package com.teguh.pokemonawesome.di.module

import com.teguh.pokemonawesome.data.repository.PokemonRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        PokemonRepository(get())
    }
}