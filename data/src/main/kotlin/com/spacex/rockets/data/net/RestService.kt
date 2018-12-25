package com.spacex.rockets.data.net

import com.spacex.rockets.data.entity.LaunchResponse
import com.spacex.rockets.data.entity.RocketResponse
import com.spacex.rockets.data.exceptions.RestException
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestService @Inject constructor(private val restApi: RestApi,
                                      private val transformers: RestTransformers) {

    fun loadAllRockets(): Flowable<List<RocketResponse>> {
        return restApi.loadAllRockets()
                .compose(transformers.parseHttpErrors<List<RocketResponse>, RestException>())
    }

    fun loadAllLaunchesByRocket(rocketId : String): Flowable<List<LaunchResponse>> {
        return restApi.loadAllLaunchesByRocket(rocketId)
            .compose(transformers.parseHttpErrors<List<LaunchResponse>, RestException>())
    }

}