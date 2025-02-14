package com.teguh.pokemonawesome.data.model

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<Type>
)