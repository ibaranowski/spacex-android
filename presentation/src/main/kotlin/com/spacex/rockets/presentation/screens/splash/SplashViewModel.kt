package com.spacex.rockets.presentation.screens.splash

import com.spacex.rockets.presentation.base.BaseViewModel
import com.spacex.rockets.presentation.base.navigation.SCREEN_MAIN
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val router: Router) : BaseViewModel() {

    companion object {
        const val TIME_DELAY_SECONDS = 3L
    }

    override fun onStart() {
        super.onStart()
        Observable.timer(TIME_DELAY_SECONDS, TimeUnit.SECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    router.newRootScreen(SCREEN_MAIN)
                }
            .addTo(disposables)
    }

}