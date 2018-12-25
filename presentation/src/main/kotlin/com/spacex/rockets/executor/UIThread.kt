package com.spacex.rockets.executor


import com.spacex.rockets.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * MainThread (UI Thread) implementation based on a [rx.Scheduler]
 * which will execute actions on the Android UI thread
 */
@Singleton
class UIThread
@Inject constructor() : PostExecutionThread {

    override fun getScheduler(): Scheduler = AndroidSchedulers.mainThread()
}