package com.david.domain.usecases

import android.content.Context
import com.david.data.callbacks.OnConnectionListener
import com.david.domain.usecasesImpl.NetworkUseCaseImpl

interface NetworkUseCase {

    fun checkConnection(onConnectionListener: OnConnectionListener)

    companion object {
        fun createInstance(context: Context): NetworkUseCase = NetworkUseCaseImpl(context)
    }
}