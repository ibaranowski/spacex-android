package com.spacex.rockets.app

import android.app.Activity
import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.spacex.rockets.BuildConfig
import com.spacex.rockets.injection.AppComponent
import com.spacex.rockets.injection.DaggerAppComponent
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector
import timber.log.Timber
import javax.inject.Inject


class App : Application(), HasActivityInjector{

    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)


        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
        appComponent.inject(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityInjector
    }
}