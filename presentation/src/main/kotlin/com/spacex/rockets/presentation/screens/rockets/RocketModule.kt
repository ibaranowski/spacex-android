package com.spacex.rockets.presentation.screens.rockets

import android.arch.lifecycle.ViewModelProvider
import com.spacex.rockets.domain.interactor.RocketInteractor
import com.spacex.rockets.injection.PerActivity
import com.spacex.rockets.injection.PerFragment
import com.spacex.rockets.presentation.base.ViewModelProviderFactory
import com.spacex.rockets.presentation.base.navigation.CustomNavigator
import com.spacex.rockets.presentation.screens.launches.LaunchesFragment
import com.spacex.rockets.presentation.screens.launches.LaunchesModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ru.terrakok.cicerone.Router

@Module
abstract class RocketModule {


    @Binds
    @PerActivity
    abstract fun bindsNavigator(navigator: RocketNavigator): CustomNavigator

    @PerFragment
    @ContributesAndroidInjector(modules = [(LaunchesModule::class)])
    abstract fun contributeLaunchesFragment(): LaunchesFragment

    @Module
    companion object {

        @Provides
        @PerActivity
        @JvmStatic
        fun provideViewModel(router: Router, rocketInteractor: RocketInteractor): RocketViewModel =
                RocketViewModel(router, rocketInteractor)

        @Provides
        @PerActivity
        @JvmStatic
        fun provideViewModelFactory(viewModel: RocketViewModel): ViewModelProviderFactory<RocketViewModel> =
                ViewModelProviderFactory(viewModel)
    }
}