package com.david.domain.callbacks

import com.david.domain.model.PokemonDetail

interface OnPokemonDetailListener {
    fun onSuccess(data: PokemonDetail)
    fun onFailure(message: String)
}