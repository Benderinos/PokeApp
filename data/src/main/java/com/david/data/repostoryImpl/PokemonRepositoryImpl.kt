package com.david.data.repostoryImpl

import com.david.data.api.PokeApi
import com.david.data.model.PokemonDetailResponse
import com.david.data.model.PokemonResponse
import com.david.data.repository.PokemonRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepositoryImpl(private val pokeApi: PokeApi) : PokemonRepository {

    override suspend fun getPokemon(number: Int, onPokemonDetailListener: PokemonRepository.OnPokemonDetailListener) {
        try {
            val response = pokeApi.getPokemon(number).await()
            onPokemonDetailListener.onSuccess(response)
        } catch (e: Exception) {
            onPokemonDetailListener.onFailure("Unable to get pokemon number: $number")
        }
        /*pokeApi.getPokemon(number).enqueue(object : Callback<PokemonDetailResponse> {
            override fun onResponse(call: Call<PokemonDetailResponse>, response: Response<PokemonDetailResponse>) {
                if (response.isSuccessful) {
                    val pokemonDetailResponse = response.body()
                    onPokemonDetailListener.onSuccess(pokemonDetailResponse!!)
                } else {
                    onPokemonDetailListener.onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<PokemonDetailResponse>, t: Throwable) {
                onPokemonDetailListener.onFailure(t.message!!)
            }
        })*/
    }

    override suspend fun getAllPokemon(onPokemonListener: PokemonRepository.OnPokemonListener) {

         try {
             val response = pokeApi.getAllPokemon(151, 0).await()
            onPokemonListener.onSuccess(response.results)
        } catch (e: Exception) {
             onPokemonListener.onFailure("Unable to get pokemons :(")
        }
        /*pokeApi.getAllPokemon(151, 0).enqueue(object : Deferred<PokemonResponse> {
            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                onPokemonListener.onFailure(t.message!!)
            }
            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                if (response.isSuccessful) {
                    val pokemonResponse = response.body()
                    val pokemonList = pokemonResponse?.results
                    if (pokemonList != null) onPokemonListener.onSuccess(pokemonList)
                } else {
                    onPokemonListener.onFailure(response.message())
                }
            }
        })*/
    }
}