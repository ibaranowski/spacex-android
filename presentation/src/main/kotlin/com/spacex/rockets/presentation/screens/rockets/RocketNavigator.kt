package com.spacex.rockets.presentation.screens.rockets

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.spacex.rockets.R
import com.spacex.rockets.presentation.base.navigation.CustomNavigator
import com.spacex.rockets.presentation.base.navigation.SCREEN_DETAILS
import com.spacex.rockets.presentation.screens.launches.LaunchesFragment
import com.spacex.rockets.presentation.screens.rockets.list.RocketItem
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

class RocketNavigator @Inject constructor(private val activity: RocketActivity)
    : CustomNavigator(activity, R.id.container) {

    override fun createFragment(screenKey: String, data: Any?): Fragment? = when (screenKey) {
        SCREEN_DETAILS -> {
            LaunchesFragment.newInstance(rocket = data as RocketItem)
        }
        else -> null
    }


    override fun setupFragmentTransactionAnimation(command: Command?,
                                                   currentFragment: Fragment?,
                                                   nextFragment: Fragment?,
                                                   fragmentTransaction: FragmentTransaction?) {
        if (command is Forward) {
                fragmentTransaction?.setCustomAnimations(
                        R.anim.inner_fragment_slide_right_in,
                        R.anim.inner_fragment_slide_left_out,
                        R.anim.inner_fragment_slide_left_in,
                        R.anim.inner_fragment_slide_right_out)
                return
        }
        super.setupFragmentTransactionAnimation(command, currentFragment, nextFragment, fragmentTransaction)
    }
}