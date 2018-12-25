package com.spacex.rockets.presentation.screens.launches

import android.arch.lifecycle.ViewModelProvider
import com.spacex.rockets.domain.interactor.RocketInteractor
import com.spacex.rockets.injection.PerFragment
import com.spacex.rockets.presentation.base.ViewModelProviderFactory
import com.spacex.rockets.presentation.screens.rockets.list.RocketItem
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router
import javax.inject.Named


@Module
abstract class LaunchesModule {

    @Module
    companion object {

        @Provides
        @PerFragment
        @JvmStatic
        fun provideViewModel(router: Router, interactor: RocketInteractor,
                             @Named(LaunchesFragment.ARG_ROCKET) rocket: RocketItem): LaunchesViewModel =
            LaunchesViewModel(router, interactor, rocket)

        @Provides
        @PerFragment
        @Named(LaunchesFragment.ARG_ROCKET)
        @JvmStatic
        fun provideRocket(fragment: LaunchesFragment): RocketItem =
            fragment.rocket

        @Provides
        @PerFragment
        @JvmStatic
        fun provideViewModelFactory(viewModel: LaunchesViewModel): ViewModelProvider.Factory =
            ViewModelProviderFactory<LaunchesViewModel>(viewModel)
    }
}