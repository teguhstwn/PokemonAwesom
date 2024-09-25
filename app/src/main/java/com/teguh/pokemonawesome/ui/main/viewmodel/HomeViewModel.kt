package com.teguh.pokemonawesome.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teguh.pokemonawesome.data.api.response.PokemonDetailResponse
import com.teguh.pokemonawesome.data.api.response.SearchResponse
import com.teguh.pokemonawesome.data.model.Pokemon
import com.teguh.pokemonawesome.data.repository.PokemonRepository
import com.teguh.pokemonawesome.utils.NetworkHelper
import com.teguh.pokemonawesome.utils.Resource
import kotlinx.coroutines.launch

class HomeViewModel (
    private val pokemonRepository: PokemonRepository,
    private val networkHelper: NetworkHelper
    ) : ViewModel() {

    private val _pokemons = MutableLiveData<Resource<SearchResponse>>()
    val pokemons: LiveData<Resource<SearchResponse>>
        get() = _pokemons

    private val _details = MutableLiveData<Resource<PokemonDetailResponse>>()
    val details: LiveData<Resource<PokemonDetailResponse>>
        get() = _details

    init {
        fetchPokemon("", 200,1)
    }

    private fun fetchPokemon(name: String, pageSize: Int, page: Int) {
        viewModelScope.launch {
            _pokemons.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()){
                pokemonRepository.searchPokemon(name, pageSize, page).let {
                    if(it.isSuccessful) {
                        _pokemons.postValue(Resource.success(it.body()))
                    } else _pokemons.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _pokemons.postValue(Resource.error("No Internet Connection", null))
        }
    }

    fun fetchDetailPokemon(id: Int){
        viewModelScope.launch {
            _details.postValue(Resource.loading(null))
            if(networkHelper.isNetworkConnected()){
                pokemonRepository.getPokemonById(id).let {
                    if(it.isSuccessful){
                        _details.postValue(Resource.success(it.body()))
                    } else _details.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }else _details.postValue(Resource.error("No Internet Connection", null))
        }
    }
}
