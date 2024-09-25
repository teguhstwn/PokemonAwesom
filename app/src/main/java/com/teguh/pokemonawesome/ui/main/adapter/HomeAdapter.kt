package com.teguh.pokemonawesome.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.teguh.pokemonawesome.R
import com.teguh.pokemonawesome.data.model.Pokemon
import com.teguh.pokemonawesome.data.model.PokemonDetail
import com.teguh.pokemonawesome.databinding.ItemPokemonBinding

class HomeAdapter (private val pokemons: MutableList<Pokemon>) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var onItemClick: ((Pokemon) -> Unit)? = null

    inner class HomeViewHolder(private val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(pokemon: Pokemon) {
            with(itemView){
                binding.tvPokemonName.text = pokemon.name
                binding.tvPokemonNumber.text = "#" + pokemon.id.toString()

                val list: MutableList<String> = mutableListOf<String>()

                for (i in pokemon.types.indices){
                    list.add(pokemon.types.get(i).name)
                }

                val separator = ", "
                val stringType = list.joinToString(separator)
                binding.tvPokemonTypeDetail.text = stringType

                if(pokemon.types.get(0).name == "normal"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_normal))
                }
                if(pokemon.types.get(0).name == "fire"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_fire))
                }
                if(pokemon.types.get(0).name == "water"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_water))
                }
                if(pokemon.types.get(0).name == "electric"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_electric))
                }
                if(pokemon.types.get(0).name == "grass"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_grass))
                }
                if(pokemon.types.get(0).name == "ice"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_ice))
                }
                if(pokemon.types.get(0).name == "fighting"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_fighting))
                }
                if(pokemon.types.get(0).name == "poison"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_poison))
                }
                if(pokemon.types.get(0).name == "ground"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_ground))
                }
                if(pokemon.types.get(0).name == "flying"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_flying))
                }
                if(pokemon.types.get(0).name == "psychic"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_psychic))
                }
                if(pokemon.types.get(0).name == "bug"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_bug))
                }
                if(pokemon.types.get(0).name == "rock"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_rock))
                }
                if(pokemon.types.get(0).name == "ghost"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_ghost))
                }
                if(pokemon.types.get(0).name == "dragon"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_dragon))
                }
                if(pokemon.types.get(0).name == "dark"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_dark))
                }
                if(pokemon.types.get(0).name == "steel"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_steel))
                }
                if(pokemon.types.get(0).name == "fairy"){
                    binding.constraint.setBackgroundColor(resources.getColor(R.color.type_fairy))
                }

                Glide.with(binding.ivPokemon.context)
                    .load(pokemon.imageUrl)
                    .into(binding.ivPokemon)

                itemView.setOnClickListener {
                    onItemClick?.invoke(pokemon)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        return HomeViewHolder(ItemPokemonBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int = pokemons.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    fun renderList(list: List<Pokemon>) {
        pokemons.clear()
        pokemons.addAll(list)
        notifyDataSetChanged()
    }

}