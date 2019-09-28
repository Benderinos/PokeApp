package com.david.pokeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.data.callbacks.OnConnectionListener
import com.david.domain.usecases.NetworkUseCase
import com.david.pokeapp.livedata.BaseSingleLiveEvent
import kotlinx.coroutines.launch

class SplashViewModel(private val networkUseCase: NetworkUseCase) : ViewModel(), OnConnectionListener {

    val goToHome: BaseSingleLiveEvent<Any?> by lazy { BaseSingleLiveEvent<Any?>() }
    val offline: BaseSingleLiveEvent<String> by lazy { BaseSingleLiveEvent<String>() }

    init {
        viewModelScope.launch {
            checkConnection()
        }
    }

    fun checkConnection(){
        networkUseCase.checkConnection(this)
    }

    override fun isOnline() {
        goToHome.call()
    }

    override fun isOffline(message: String) {
        offline.value = message
    }
}