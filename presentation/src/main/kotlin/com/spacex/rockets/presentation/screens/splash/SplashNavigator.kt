package com.spacex.rockets.presentation.screens.splash

import android.content.Intent
import com.spacex.rockets.presentation.base.navigation.CustomNavigator
import com.spacex.rockets.presentation.base.navigation.SCREEN_MAIN
import com.spacex.rockets.presentation.screens.rockets.RocketActivity
import javax.inject.Inject

class SplashNavigator @Inject constructor(private val activity: SplashActivity)
    : CustomNavigator(activity) {

    override fun createActivityIntent(screenKey: String, data: Any?): Intent? = when (screenKey) {
        SCREEN_MAIN -> {
            RocketActivity.getIntent(activity)
        }
        else -> null
    }
}