package com.spacex.rockets.presentation.utils.extensions

import com.spacex.rockets.app.DEBOUNCE_DELAY_MS
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import java.util.concurrent.TimeUnit

fun <T : Any> Flowable<T>.applyThrottlingFirst(): Flowable<T> = compose(applyThrottlingFirstFlowable<T>())

fun <T : Any> Observable<T>.applyThrottlingFirst(): Observable<T> = compose(applyThrottlingFirstObservable<T>())

private fun <T : Any> applyThrottlingFirstObservable(): ObservableTransformer<T, T> = ObservableTransformer {
    it.throttleFirst(DEBOUNCE_DELAY_MS, TimeUnit.MILLISECONDS)
}

private fun <T : Any> applyThrottlingFirstFlowable(): FlowableTransformer<T, T> = FlowableTransformer {
    it.throttleFirst(DEBOUNCE_DELAY_MS, TimeUnit.MILLISECONDS)
}

fun <T : Any> Flowable<T>.applyThrottlingLast(): Flowable<T> = compose(applyThrottlingLastFlowable<T>())

fun <T : Any> Observable<T>.applyThrottlingLast(): Observable<T> = compose(applyThrottlingLastObservable<T>())

private fun <T : Any> applyThrottlingLastObservable(): ObservableTransformer<T, T> = ObservableTransformer {
    it.throttleLast(DEBOUNCE_DELAY_MS, TimeUnit.MILLISECONDS)
}

private fun <T : Any> applyThrottlingLastFlowable(): FlowableTransformer<T, T> = FlowableTransformer {
    it.throttleLast(DEBOUNCE_DELAY_MS, TimeUnit.MILLISECONDS)
}