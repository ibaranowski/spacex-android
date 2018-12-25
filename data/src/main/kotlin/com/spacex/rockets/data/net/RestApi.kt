package com.spacex.rockets.data.net

import com.spacex.rockets.data.entity.LaunchResponse
import com.spacex.rockets.data.entity.RocketResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("/v3/rockets")
    fun loadAllRockets(): Flowable<List<RocketResponse>>

    @GET("/v3/launches")
    fun loadAllLaunchesByRocket(@Query("rocket_id") rocketId : String): Flowable<List<LaunchResponse>>

}