package com.david.pokeapp.viewmodel

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.david.domain.callbacks.OnPokemonListListener
import com.david.domain.model.Pokemon
import com.david.domain.usecases.PokemonUseCase
import com.david.pokeapp.livedata.BaseSingleLiveEvent
import com.david.pokeapp.ui.home.HomeFragmentDirections
import kotlinx.coroutines.launch

class HomeViewModel(private val pokemonUseCase: PokemonUseCase, private val context: Context) :
    ViewModel(), OnPokemonListListener {

    val pokemons: BaseSingleLiveEvent<List<Pokemon>> by lazy { BaseSingleLiveEvent<List<Pokemon>>() }
    val filteredPokemonList: BaseSingleLiveEvent<List<Pokemon>> by lazy { BaseSingleLiveEvent<List<Pokemon>>() }
    val goToPokemonDetail: BaseSingleLiveEvent<Map<HomeFragmentDirections.ActionHomeFragmentToPokemonDetail, FragmentNavigator.Extras>> by lazy { BaseSingleLiveEvent<Map<HomeFragmentDirections.ActionHomeFragmentToPokemonDetail, FragmentNavigator.Extras>>() }
    val errorMessage: BaseSingleLiveEvent<String> by lazy { BaseSingleLiveEvent<String>() }

    fun loadPokemonList() {
        viewModelScope.launch { getAllPokemonList() }
    }

    private fun getAllPokemonList() {
        pokemonUseCase.getAllPokemonList(this)
    }

    fun pokemonClicked(pokemon: Pokemon, imageView: ImageView, textView: TextView) {
        val extras = FragmentNavigatorExtras(
            imageView to pokemon.number.toString(),
            textView to pokemon.name
        )
        val direction = HomeFragmentDirections.actionHomeFragmentToPokemonDetail(pokemon.number, pokemon.spriteUrl, pokemon.name)
        val directionData  = HashMap<HomeFragmentDirections.ActionHomeFragmentToPokemonDetail, FragmentNavigator.Extras>()
        directionData[direction] = extras
        goToPokemonDetail.value = directionData
    }

    override fun onSuccess(data: List<Pokemon>) {
        pokemons.value = data
    }

    override fun onFailure(message: String) {
        errorMessage.value = message
    }

    fun performSearch(newText: String?) {
        val searchedPokemons = arrayListOf<Pokemon>()
        if(newText!=null){
            pokemons.value?.forEach {
                it.takeIf { it.name.contains(newText) }?.apply { searchedPokemons.add(it) }
            }.apply { filteredPokemonList.value = searchedPokemons }
        }else{
            getAllPokemonList()
        }
    }
}