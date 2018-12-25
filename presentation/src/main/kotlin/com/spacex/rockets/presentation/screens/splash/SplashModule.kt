package com.spacex.rockets.presentation.screens.splash

import com.spacex.rockets.injection.PerActivity
import com.spacex.rockets.presentation.base.ViewModelProviderFactory
import com.spacex.rockets.presentation.base.navigation.CustomNavigator
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
abstract class SplashModule {

    @Binds
    @PerActivity
    abstract fun bindsNavigator(navigator: SplashNavigator): CustomNavigator

    @Module
    companion object {

        @Provides
        @PerActivity
        @JvmStatic
        fun provideViewModel(router: Router): SplashViewModel =
            SplashViewModel(router)

        @Provides
        @PerActivity
        @JvmStatic
        fun provideViewModelFactory(viewModel: SplashViewModel): ViewModelProviderFactory<SplashViewModel> =
            ViewModelProviderFactory(viewModel)
    }
}