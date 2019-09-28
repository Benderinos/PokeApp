package com.david.pokeapp.ui.splash

import androidx.lifecycle.Observer
import com.david.pokeapp.R
import com.david.pokeapp.base.BaseActivity
import com.david.pokeapp.ui.home.HomeActivity
import com.david.pokeapp.viewmodel.SplashViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun initObservers() {
        viewModel.goToHome.observe(this, Observer {
            goToHome()
        })
        viewModel.offline.observe(this, Observer {
            Snackbar.make(tvSplash, it, Snackbar.LENGTH_INDEFINITE)
                .setAction(resources.getString(R.string.retry)) { viewModel.checkConnection() }
                .show()
        })
    }

    private fun goToHome() {
        goToActivity(HomeActivity(), this)
    }

    override fun destroyView() {
        viewModel.goToHome.removeObservers(this)
    }
}
