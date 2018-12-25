package com.spacex.rockets.presentation.utils

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.spacex.rockets.presentation.base.BaseViewModel

inline fun <reified T : BaseViewModel> buildViewModel(
        activity: FragmentActivity,
        factory: ViewModelProvider.Factory = ViewModelProvider.AndroidViewModelFactory.getInstance(activity.application)
): T = ViewModelProviders.of(activity, factory).get(T::class.java)

inline fun <reified T : BaseViewModel> buildViewModel(
        fragment: Fragment,
        factory: ViewModelProvider.Factory = ViewModelProvider.AndroidViewModelFactory.getInstance(fragment.requireContext().applicationContext as Application)
): T = ViewModelProviders.of(fragment, factory).get(T::class.java)
