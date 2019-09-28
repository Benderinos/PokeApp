package com.david.data.model

class PokemonData(
    var number: Int,
    var name: String,
    var url: String
) {
    fun getNumberFromUrl(): Int {
        val urlSplit = url.split("/")
        val number = Integer.parseInt(urlSplit[urlSplit.size - 2])
        return number
    }

    fun getSpriteUrlFromNumber(number : Int): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$number.png"
    }
}

