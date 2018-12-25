package com.spacex.rockets.presentation.screens.rockets

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.spacex.rockets.domain.interactor.RocketInteractor
import com.spacex.rockets.presentation.base.BaseViewModel
import com.spacex.rockets.presentation.base.navigation.SCREEN_DETAILS
import com.spacex.rockets.presentation.screens.rockets.list.RocketAdapter
import com.spacex.rockets.presentation.screens.rockets.list.RocketItem
import com.spacex.rockets.presentation.screens.rockets.list.mapToAdapter
import com.spacex.rockets.presentation.utils.extensions.applyThrottlingFirst
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject


class RocketViewModel @Inject constructor(
    private val router: Router,
    val rocketInteractor: RocketInteractor
) : BaseViewModel() {

    val rocketAdapter = RocketAdapter()
    var list: List<RocketItem>? = null

    init {

        rocketAdapter
            .clickSubject
            .applyThrottlingFirst()
            .subscribeBy {
                Timber.e("clicked %s", it.position)
                router.navigateTo(SCREEN_DETAILS, it.model)
            }

    }


    override fun onStart() {
        super.onStart()
        updateRockets()

    }

    fun updateRockets() {
        showProgress()
        rocketInteractor
            .getRockets()
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.map { it.mapToAdapter() }
            }
            .subscribeBy(
                onNext = {
                    list = it
                    rocketAdapter.setItems(it)
                    dismissProgress()
                },
                onError = {
                    dismissProgress()
                }

            )
            .addTo(disposables)

    }

    fun filterActive(active: Boolean) {
        list?.also {
            rocketAdapter.setItems(
                if (active)
                    it.filter { it.active == active }
                else it)
        }

    }

    fun backPressed() {
        router.exit()
    }
}