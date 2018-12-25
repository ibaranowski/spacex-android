package com.spacex.rockets.domain.interactor

import com.spacex.rockets.domain.entity.LaunchEntity
import com.spacex.rockets.domain.entity.RocketEntity
import com.spacex.rockets.domain.executor.PostExecutionThread
import com.spacex.rockets.domain.providers.RocketProvider
import io.reactivex.Flowable
import javax.inject.Inject

class RocketInteractor @Inject constructor(postExecutionThread: PostExecutionThread,
                                           private val rocketProvider: RocketProvider)
    : BaseInteractor(postExecutionThread) {

    fun getRockets(): Flowable<List<RocketEntity>> =
        rocketProvider.get()
                    .subscribeOn(threadExecutor)
                    .observeOn(postExecutionThread)

    fun getLaunchesByRocket(rocketId: String): Flowable<List<LaunchEntity>> =
        rocketProvider.getLaunchesByRocket(rocketId)
            .subscribeOn(threadExecutor)
            .observeOn(postExecutionThread)
}