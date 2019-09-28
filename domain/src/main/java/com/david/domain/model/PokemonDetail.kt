package com.david.domain.model

import com.david.data.model.PokemonDetailResponse
import com.david.data.model.Types

class PokemonDetail{
    var types : List<Types> = arrayListOf()

    companion object{
        fun map(pokemonDetailData: PokemonDetailResponse) : PokemonDetail{
            val pokemon = PokemonDetail()
            pokemon.types = pokemonDetailData.types
            return pokemon
        }
    }
}
