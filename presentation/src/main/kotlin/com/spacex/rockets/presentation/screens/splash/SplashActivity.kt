package com.spacex.rockets.presentation.screens.splash

import com.spacex.rockets.R
import com.spacex.rockets.databinding.ActivitySplashBinding
import com.spacex.rockets.presentation.base.BaseMvvmActivity
import com.spacex.rockets.presentation.base.ViewModelProviderFactory
import com.spacex.rockets.presentation.base.navigation.CustomNavigator
import com.spacex.rockets.presentation.utils.buildViewModel
import javax.inject.Inject

class SplashActivity: BaseMvvmActivity<ActivitySplashBinding, SplashViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory<SplashViewModel>

    @Inject
    override lateinit var navigator: CustomNavigator


    override fun provideLayoutId(): Int = R.layout.activity_splash

    override fun provideViewModel(): SplashViewModel =
        buildViewModel<SplashViewModel>(this, viewModelFactory)
}