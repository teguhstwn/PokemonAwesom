package com.teguh.pokemonawesome.data.api.response

import com.teguh.pokemonawesome.data.model.PokemonDetail

class PokemonDetailResponse(
    val success: Boolean,
    val message: String? = null,
    val pokemon: PokemonDetail? = null
)