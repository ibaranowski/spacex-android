package com.spacex.rockets.presentation.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.spacex.rockets.app.PROGRESS_DELAY_MS
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

abstract class BaseViewModel : ViewModel() {

    protected val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    val progressVisible = ObservableBoolean(false)
    private var progressDelayDisposable: Disposable? = null

    fun handleBaseError(throwable: Throwable) = throwable.printStackTrace()

    fun showProgress() {
        progressVisible.set(true)
    }

    fun showProgressWithDelay() {
        progressDelayDisposable = Observable.timer(PROGRESS_DELAY_MS, TimeUnit.MILLISECONDS,
                Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(1)
                .doOnNext { progressVisible.set(true) }
                .subscribeBy()

    }

    fun dismissProgress() {

        progressDelayDisposable?.dispose()
        progressVisible.set(false)
    }

    override fun onCleared() {
        dismissProgress()
        disposables.clear()
        super.onCleared()
    }

    open fun onStart() {}

    open fun onStop() {}

    open fun onResume() {}

    open fun onPause() {}
}