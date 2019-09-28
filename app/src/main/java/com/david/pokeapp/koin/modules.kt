package com.david.pokeapp.koin

import com.david.pokeapp.viewmodel.HomeViewModel
import com.david.pokeapp.viewmodel.PokemonDetailViewModel
import com.david.pokeapp.viewmodel.SearchViewModel
import com.david.pokeapp.viewmodel.SplashViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import kotlin.coroutines.CoroutineContext

val appModule = module {

    viewModel { SplashViewModel(networkUseCase = get()) }
    viewModel { HomeViewModel(pokemonUseCase = get(), context = get()) }
    viewModel { PokemonDetailViewModel(pokemonUseCase = get()) }
    viewModel { SearchViewModel() }

    // Coroutines
    factory<CoroutineContext> { Dispatchers.Main }
}