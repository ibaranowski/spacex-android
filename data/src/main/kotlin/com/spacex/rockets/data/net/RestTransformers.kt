package com.spacex.rockets.data.net

import com.google.gson.Gson
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.functions.Function
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RestTransformers @Inject constructor(val gson: Gson) {

    inline fun <T, reified R : Throwable> parseHttpErrors(): FlowableTransformer<T, T> {
        return FlowableTransformer<T, T> { upstream ->
            upstream.onErrorResumeNext(Function<Throwable, Flowable<T>> { throwable ->
                if (throwable is HttpException) {
                    val errorBody = throwable.response().errorBody()!!.string()
                    val errorObj = gson.fromJson(errorBody, R::class.java)
                    Flowable.error<T>(errorObj)
                } else {
                    Flowable.error<T>(throwable)
                }
            })
        }
    }
}