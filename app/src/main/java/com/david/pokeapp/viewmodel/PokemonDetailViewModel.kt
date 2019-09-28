package com.david.pokeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.domain.callbacks.OnPokemonDetailListener
import com.david.domain.model.PokemonDetail
import com.david.domain.usecases.PokemonUseCase
import com.david.pokeapp.livedata.BaseSingleLiveEvent
import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val pokemonUseCase: PokemonUseCase) : ViewModel(), OnPokemonDetailListener {

    val pokemon: BaseSingleLiveEvent<PokemonDetail> by lazy { BaseSingleLiveEvent<PokemonDetail>() }
    val errorMessage: BaseSingleLiveEvent<String> by lazy { BaseSingleLiveEvent<String>() }

    fun loadPokemon(pokemonNumber: Int){
        viewModelScope.launch {
            getPokemon(pokemonNumber)
        }
    }

    private fun getPokemon(pokemonNumber: Int) {
        pokemonUseCase.getPokemon(pokemonNumber ,this)
    }

    override fun onSuccess(data: PokemonDetail) {
        pokemon.value = data
    }

    override fun onFailure(message: String) {
        errorMessage.value = message
    }
}