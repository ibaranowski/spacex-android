package com.spacex.rockets.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getScheduler() : Scheduler
}
