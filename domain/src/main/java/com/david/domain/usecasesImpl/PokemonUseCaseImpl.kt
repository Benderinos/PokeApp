package com.david.domain.usecasesImpl

import com.david.data.model.PokemonData
import com.david.data.model.PokemonDetailResponse
import com.david.data.repository.PokemonRepository
import com.david.domain.callbacks.OnPokemonDetailListener
import com.david.domain.callbacks.OnPokemonListListener
import com.david.domain.model.Pokemon
import com.david.domain.model.PokemonDetail
import com.david.domain.usecases.PokemonUseCase

class PokemonUseCaseImpl(private val pokemonRepository: PokemonRepository) : PokemonUseCase {

    override suspend fun getAllPokemonList(onPokemonListLisener: OnPokemonListListener) {
        pokemonRepository.getAllPokemon(object : PokemonRepository.OnPokemonListener {
            override fun onSuccess(pokemonDataList: List<PokemonData>) {
                val pokemonListDomain = Pokemon.map(pokemonDataList)
                onPokemonListLisener.onSuccess(pokemonListDomain)
            }

            override fun onFailure(message: String) {
                onPokemonListLisener.onFailure(message)
            }
        })
    }

    override suspend fun getPokemon(pokemonNumber: Int, onPokemonDetailListLisener: OnPokemonDetailListener) {
        pokemonRepository.getPokemon(pokemonNumber, object : PokemonRepository.OnPokemonDetailListener {
            override fun onSuccess(pokemonDetailResponse: PokemonDetailResponse) {
                val pokemon = PokemonDetail.map(pokemonDetailResponse)
                onPokemonDetailListLisener.onSuccess(pokemon)
            }

            override fun onFailure(message: String) {
            }
        })
    }

}