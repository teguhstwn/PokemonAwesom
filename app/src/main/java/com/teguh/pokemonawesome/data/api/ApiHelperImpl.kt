package com.teguh.pokemonawesome.data.api

import com.teguh.pokemonawesome.data.api.response.PokemonDetailResponse
import com.teguh.pokemonawesome.data.api.response.SearchResponse
import com.teguh.pokemonawesome.data.model.Pokemon
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun searchPokemon(name: String, pageSize: Int, page: Int): Response<SearchResponse> = apiService.searchPokemons(name, pageSize, page)

    override suspend fun getPokemonById(id: Int): Response<PokemonDetailResponse> = apiService.getPokemonById(id)
}