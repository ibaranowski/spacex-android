package com.spacex.rockets.presentation.base.navigation

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.spacex.rockets.R
import com.spacex.rockets.presentation.base.BaseActivity
import ru.terrakok.cicerone.android.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

open class CustomNavigator(private val activity: BaseActivity, containerId: Int = 0)
    : SupportAppNavigator(activity, containerId) {


    override fun createFragment(screenKey: String, data: Any?): Fragment? = null

    override fun createActivityIntent(screenKey: String, data: Any?): Intent? = null

    override fun setupFragmentTransactionAnimation(command: Command?,
                                                   currentFragment: Fragment?,
                                                   nextFragment: Fragment?,
                                                   fragmentTransaction: FragmentTransaction?) {
        fragmentTransaction?.setCustomAnimations(R.anim.screen_fade_in, R.anim.screen_fade_out)
    }
}