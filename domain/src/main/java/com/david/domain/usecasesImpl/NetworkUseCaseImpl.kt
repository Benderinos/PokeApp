package com.david.domain.usecasesImpl

import android.content.Context
import com.david.data.callbacks.OnConnectionListener
import com.david.domain.usecases.NetworkUseCase
import android.net.ConnectivityManager
import com.david.domain.R

class NetworkUseCaseImpl(private val context: Context) : NetworkUseCase {

    override fun checkConnection(onConnectionListener: OnConnectionListener) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        if(isConnected) onConnectionListener.isOnline() else onConnectionListener.isOffline(context.resources.getString(R.string.no_connection))
    }

}