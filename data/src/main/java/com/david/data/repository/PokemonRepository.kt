package com.david.data.repository

import com.david.data.model.PokemonData
import com.david.data.model.PokemonDetailResponse

interface PokemonRepository {

    fun getAllPokemon(onPokemonListener: OnPokemonListener)
    fun getPokemon(number : Int, onPokemonDetailListener: OnPokemonDetailListener)

    interface OnPokemonListener {
        fun onSuccess(pokemonDataList : List<PokemonData>)
        fun onFailure(message: String)
    }

    interface OnPokemonDetailListener {
        fun onSuccess(pokemonDetailResponse: PokemonDetailResponse)
        fun onFailure(message: String)
    }
}