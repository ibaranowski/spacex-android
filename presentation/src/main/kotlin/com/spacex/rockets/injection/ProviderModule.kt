package com.spacex.rockets.injection

import com.spacex.rockets.data.providers.RocketProviderImpl
import com.spacex.rockets.domain.providers.RocketProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ProviderModule {

    @Binds
    abstract fun rocketProvider(provider: RocketProviderImpl): RocketProvider

}