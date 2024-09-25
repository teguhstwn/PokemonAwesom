package com.teguh.pokemonawesome.data.api

import com.teguh.pokemonawesome.data.api.response.PokemonDetailResponse
import com.teguh.pokemonawesome.data.api.response.SearchResponse
import com.teguh.pokemonawesome.data.model.Pokemon
import retrofit2.Response

interface ApiHelper {
    suspend fun searchPokemon(name: String, pageSize: Int, page: Int): Response<SearchResponse>
    suspend fun getPokemonById(id: Int): Response<PokemonDetailResponse>
}