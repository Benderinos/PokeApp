package com.david.domain.usecases

import com.david.data.repository.PokemonRepository
import com.david.domain.callbacks.OnPokemonDetailListener
import com.david.domain.callbacks.OnPokemonListListener
import com.david.domain.model.Pokemon
import com.david.domain.usecasesImpl.PokemonUseCaseImpl

interface PokemonUseCase {

    fun getAllPokemonList(onPokemonListLisener : OnPokemonListListener)
    fun getPokemon(pokemonNumber : Int, onPokemonDetailListLisener : OnPokemonDetailListener)

    companion object {
        fun createInstance(pokemonRepository: PokemonRepository): PokemonUseCase = PokemonUseCaseImpl(pokemonRepository)
    }
}