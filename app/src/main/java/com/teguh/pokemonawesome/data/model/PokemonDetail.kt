package com.teguh.pokemonawesome.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDetail(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val weight: Int,
    val height: Int,
    val abilities: List<Ability>,
    val stats: List<Stat>,
    val types: List<Type>
) : Parcelable

@Parcelize
data class Ability(
    val name: String
) : Parcelable

@Parcelize
data class Stat(
    val name: String,
    val base_stat: Int
) : Parcelable

@Parcelize
data class Type(
    val name: String
) : Parcelable
