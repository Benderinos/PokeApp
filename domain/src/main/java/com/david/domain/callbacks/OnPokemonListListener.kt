package com.david.domain.callbacks

import com.david.domain.model.Pokemon

interface OnPokemonListListener {
    fun onSuccess(data: List<Pokemon>)
    fun onFailure(message: String)
}