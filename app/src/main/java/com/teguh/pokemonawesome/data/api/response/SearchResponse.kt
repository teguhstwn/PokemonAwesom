package com.teguh.pokemonawesome.data.api.response

import com.teguh.pokemonawesome.data.model.Pokemon

class SearchResponse(
    val success: Boolean,
    val message: String? = null,
    val previousPage: Int? = null,
    val nextPage: Int? = null,
    val pokemons: List<Pokemon> = emptyList()
)