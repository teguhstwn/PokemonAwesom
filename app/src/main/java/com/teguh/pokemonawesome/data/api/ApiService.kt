package com.teguh.pokemonawesome.data.api

import com.teguh.pokemonawesome.data.api.response.PokemonDetailResponse
import com.teguh.pokemonawesome.data.api.response.SearchResponse
import com.teguh.pokemonawesome.data.model.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("pokemons/search")
    suspend fun searchPokemons(
        @Query("name") name: String,
        @Query("pageSize") pageSize: Int = 20,
        @Query("page") page: Int = 1
    ): Response<SearchResponse>

    @GET("pokemons/detail")
    suspend fun getPokemonById(
        @Query("id") pokemonId: Int
    ): Response<PokemonDetailResponse>

}