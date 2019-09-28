package com.david.domain.koin

import com.david.domain.usecases.NetworkUseCase
import com.david.domain.usecases.PokemonUseCase
import org.koin.dsl.module.module

val domainModule = module {

    /**
     * Use cases
     */
    single { NetworkUseCase.createInstance(context = get()) }
    single { PokemonUseCase.createInstance(pokemonRepository = get()) }
}