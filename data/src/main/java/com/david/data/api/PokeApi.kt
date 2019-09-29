package com.david.data.api

import com.david.data.model.PokemonDetailResponse
import com.david.data.model.PokemonResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    fun getAllPokemon(@Query("limit") limit: Int, @Query("offset") offset : Int): Deferred<PokemonResponse>

    @GET("pokemon/{number}/")
    fun getPokemon(@Path("number") pokemon: Int): Deferred<PokemonDetailResponse>
}