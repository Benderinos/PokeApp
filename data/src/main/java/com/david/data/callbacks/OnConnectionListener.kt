package com.david.data.callbacks

interface OnConnectionListener{
    fun isOnline()
    fun isOffline(message: String)
}