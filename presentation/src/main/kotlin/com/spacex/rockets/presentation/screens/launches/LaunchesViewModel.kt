package com.spacex.rockets.presentation.screens.launches

import android.databinding.ObservableField
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarEntry
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.spacex.rockets.domain.interactor.RocketInteractor
import com.spacex.rockets.presentation.base.BaseViewModel
import com.spacex.rockets.presentation.screens.rockets.list.RocketItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import com.spacex.rockets.domain.entity.LaunchEntity
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.data.BarDataSet
import com.spacex.rockets.presentation.screens.launches.list.LaunchAdapter
import com.spacex.rockets.presentation.screens.launches.list.LaunchItem
import com.spacex.rockets.presentation.screens.launches.list.mapToAdapter
import kotlinx.coroutines.*
import timber.log.Timber


class LaunchesViewModel @Inject constructor(
    private val router: Router,
    val rocketInteractor: RocketInteractor,
    val rocket: RocketItem
) : BaseViewModel() {


    val graphSeries = ObservableField<BarData>()
    val launchesAdapter = LaunchAdapter()



    override fun onStart() {
        super.onStart()
        updateLaunches()

    }

    fun updateLaunches() {
        Timber.e("updateLaunches")
        showProgress()
        rocketInteractor
            .getLaunchesByRocket(rocket.id)
            .map {
                it.groupBy { it.launchYear }
                    .map { it.value }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onNext = {
                    setChart(it)
                    setHistory(it)
                    dismissProgress()
                },
                onError = {
                    dismissProgress()
                }

            )
            .addTo(disposables)

    }

    fun setChart(it: List<List<LaunchEntity>>){

        val values = it.map {
            BarEntry(it[0].launchYear.toFloat(), it.size.toFloat())
        }
        val data = BarData(arrayListOf<IBarDataSet>(BarDataSet(values, "Launches")))
        graphSeries.set(data)
    }


    private val uiScope = CoroutineScope(Dispatchers.Main)
    fun setHistory(it : List<List<LaunchEntity>>) {
        uiScope.launch {
            val deferred = async(Dispatchers.Default) {
                delay(300)
                it.map {
                    it.mapIndexed { index, launchEntity ->
                        launchEntity.mapToAdapter(index)
                    }
                }.flatten()
            }.await()
            launchesAdapter.setItems(deferred)
        }
    }

    fun backPressed() {
        router.exit()
    }
}