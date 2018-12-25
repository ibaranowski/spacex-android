package com.spacex.rockets.data.providers

import com.spacex.rockets.data.database.AppDatabase
import com.spacex.rockets.data.database.mapToDatabase
import com.spacex.rockets.data.entity.mapToDomain
import com.spacex.rockets.data.net.RestService
import com.spacex.rockets.domain.entity.LaunchEntity
import com.spacex.rockets.domain.entity.RocketEntity
import com.spacex.rockets.domain.providers.RocketProvider
import io.reactivex.Completable
import io.reactivex.Flowable
import timber.log.Timber
import javax.inject.Inject

class RocketProviderImpl @Inject constructor(private val restService: RestService,
                                             private val db: AppDatabase) : RocketProvider {

    override fun get(): Flowable<List<RocketEntity>> =
        db.rocketDao.get()
            .flatMap {
                if (!it.isNullOrEmpty()) {
                    Flowable.just (
                        it.map {
                        it.mapToDomain()
                    })
                } else {
                    restService
                        .loadAllRockets()
                        .onErrorReturn {
                            throw it
                        }.map {

                           db.rocketDao.insert(it.map { it.mapToDatabase() })

                            it.map { it.mapToDomain() }
                        }
                }
            }



    override fun getLaunchesByRocket(rocketId: String): Flowable<List<LaunchEntity>> =

        db.launchesDao
            .get(rocketId)
            .take(1)
            .flatMap {


                if (!it.isEmpty()) {

                    Timber.e("****offline***size %s",it.size)
                    it.forEach {
                        Timber.e(it.missionName)
                    }
                    Flowable.just(
                        it.map {
                            it.mapToDomain()
                        })
                } else {

                    restService
                        .loadAllLaunchesByRocket(rocketId)
                        .doOnNext {
                            Timber.e("****online***** size %s",it.size)
                            val data = it.map { it.mapToDatabase(rocketId)}
                            data.forEach {
                                Timber.e(it.missionName)
                            }
                            db.launchesDao.insert(data) }
                        .onErrorReturn {
                            throw it
                        }
                        .map {
                            it.map { it.mapToDomain() }
                        }
                }


            }


}