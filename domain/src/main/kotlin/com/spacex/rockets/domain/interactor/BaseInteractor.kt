package com.spacex.rockets.domain.interactor

import com.spacex.rockets.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor

abstract class BaseInteractor(protected val threadExecutor: Scheduler,
                              protected val postExecutionThread: Scheduler) {

    constructor(postExecutionThread: PostExecutionThread, threadExecutor: Executor)
            : this(Schedulers.from(threadExecutor), postExecutionThread.getScheduler())

    constructor(postExecutionThread: PostExecutionThread, threadExecutor: Scheduler = Schedulers.io())
            : this(threadExecutor, postExecutionThread.getScheduler())
}