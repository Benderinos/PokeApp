package com.david.domain.model

import com.david.data.model.PokemonData

class Pokemon {

    var number: Int = 0
    var name: String = ""
    var url: String = ""
    var spriteUrl = ""

    companion object{
        fun map(pokemonDataList: List<PokemonData>) : List<Pokemon>{
            val pokemonList = arrayListOf<Pokemon>()
            pokemonDataList.forEach {
                val pokemon = Pokemon()
                pokemon.name = it.name
                pokemon.number = it.getNumberFromUrl()
                pokemon.url = it.url
                pokemon.spriteUrl = it.getSpriteUrlFromNumber(pokemon.number)
                pokemonList.add(pokemon)
            }
            return pokemonList
        }
    }
}