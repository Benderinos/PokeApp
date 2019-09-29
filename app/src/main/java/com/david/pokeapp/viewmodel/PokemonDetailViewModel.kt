package com.david.pokeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.domain.callbacks.OnPokemonDetailListener
import com.david.domain.model.PokemonDetail
import com.david.domain.usecases.PokemonUseCase
import com.david.pokeapp.livedata.BaseSingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val pokemonUseCase: PokemonUseCase) : ViewModel(), OnPokemonDetailListener {

    val pokemon: BaseSingleLiveEvent<PokemonDetail> by lazy { BaseSingleLiveEvent<PokemonDetail>() }
    val errorMessage: BaseSingleLiveEvent<String> by lazy { BaseSingleLiveEvent<String>() }

    fun loadPokemon(pokemonNumber: Int){
        viewModelScope.launch {
            getPokemon(pokemonNumber)
        }
    }

    private suspend fun getPokemon(pokemonNumber: Int) {
        viewModelScope.launch(Dispatchers.Main){pokemonUseCase.getPokemon(pokemonNumber ,this@PokemonDetailViewModel)}
    }

    override fun onSuccess(data: PokemonDetail) {
        viewModelScope.launch(Dispatchers.Main){pokemon.value = data}
    }

    override fun onFailure(message: String) {
        viewModelScope.launch(Dispatchers.Main){errorMessage.value = message}
    }
}