package com.teguh.pokemonawesome.data.repository

import com.teguh.pokemonawesome.data.api.ApiHelper

class PokemonRepository (private val apiHelper: ApiHelper){
    suspend fun searchPokemon(name: String, pageSize: Int, page: Int) = apiHelper.searchPokemon(name, pageSize, page)
    suspend fun getPokemonById(id: Int) = apiHelper.getPokemonById(id)
}