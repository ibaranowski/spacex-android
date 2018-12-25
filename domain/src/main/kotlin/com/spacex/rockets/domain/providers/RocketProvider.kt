package com.spacex.rockets.domain.providers

import com.spacex.rockets.domain.entity.LaunchEntity
import com.spacex.rockets.domain.entity.RocketEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface RocketProvider {
    fun get(): Flowable<List<RocketEntity>>
    fun getLaunchesByRocket(rocketId: String): Flowable<List<LaunchEntity>>
}