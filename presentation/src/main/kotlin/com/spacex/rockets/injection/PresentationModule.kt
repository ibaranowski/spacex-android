package com.spacex.rockets.injection

import android.content.Context
import com.spacex.rockets.app.App
import com.spacex.rockets.presentation.screens.rockets.RocketActivity
import com.spacex.rockets.presentation.screens.rockets.RocketModule
import com.spacex.rockets.presentation.screens.splash.SplashActivity
import com.spacex.rockets.presentation.screens.splash.SplashModule
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
abstract class PresentationModule {

    @Module
    companion object {

        private val cicerone = Cicerone.create(Router())

        @Provides
        @Singleton
        @JvmStatic
        fun provideApplicationContext(app: App): Context = app.applicationContext

        @Provides
        @Singleton
        @JvmStatic
        fun provideRouter(): Router = cicerone.router

        @Provides
        @Singleton
        @JvmStatic
        fun provideNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder

    }


    @PerActivity
    @ContributesAndroidInjector(modules = [(RocketModule::class)])
    abstract fun contributeRocketActivity(): RocketActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [(SplashModule::class)])
    abstract fun contributeSplashActivity(): SplashActivity

}