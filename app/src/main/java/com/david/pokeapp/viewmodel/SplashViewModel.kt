package com.david.pokeapp.viewmodel

import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.data.callbacks.OnConnectionListener
import com.david.domain.usecases.NetworkUseCase
import com.david.pokeapp.livedata.BaseSingleLiveEvent
import kotlinx.coroutines.launch

class SplashViewModel(private val networkUseCase: NetworkUseCase) : ViewModel(), OnConnectionListener {

    val goToHome: BaseSingleLiveEvent<Any?> by lazy { BaseSingleLiveEvent<Any?>() }
    val startAnimation: BaseSingleLiveEvent<Any?> by lazy { BaseSingleLiveEvent<Any?>() }
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
        startAnimation.call()
    }

    override fun isOffline(message: String) {
        offline.value = message
    }

    fun fadeInAnimation(view: View?) {
        view?.visibility = View.VISIBLE
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.interpolator = AccelerateInterpolator()
        fadeIn.duration = 1500
        fadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation) {
                goToHome.call()
            }
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationStart(animation: Animation) {}
        })
        view?.startAnimation(fadeIn)
    }

    fun fadeOutAnimation(tvSplash: View?, ivSplash : View) {
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = AccelerateInterpolator()
        fadeOut.duration = 2500
        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation) {
                tvSplash?.visibility = View.GONE
                fadeInAnimation(ivSplash)
            }

            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationStart(animation: Animation) {}
        })
        tvSplash?.startAnimation(fadeOut)
    }
}