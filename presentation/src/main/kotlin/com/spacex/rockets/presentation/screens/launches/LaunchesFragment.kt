package com.spacex.rockets.presentation.screens.launches

import android.arch.lifecycle.ViewModelProvider
import android.databinding.Observable
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.github.mikephil.charting.components.AxisBase
import com.spacex.rockets.R
import com.spacex.rockets.databinding.FragmentLaunchesBinding
import com.spacex.rockets.presentation.base.BaseMvvmFragment
import com.spacex.rockets.presentation.base.recycler.HeaderItemDecoration
import com.spacex.rockets.presentation.screens.rockets.list.RocketItem
import com.spacex.rockets.presentation.utils.buildViewModel
import javax.inject.Inject
import com.spacex.rockets.presentation.base.recycler.VerticalItemDecoration


class LaunchesFragment : BaseMvvmFragment<FragmentLaunchesBinding, LaunchesViewModel>() {

    companion object {
        const val ARG_ROCKET = "ARG_ROCKET"
        fun newInstance(rocket: RocketItem) =
                LaunchesFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_ROCKET, rocket)
                    }
                }
    }


    val rocket: RocketItem
            get() = arguments?.getSerializable(ARG_ROCKET) as RocketItem

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun provideLayoutId(): Int = R.layout.fragment_launches

    override fun provideViewModel(): LaunchesViewModel = buildViewModel(this, viewModelFactory)

    private val graphListener = object : Observable.OnPropertyChangedCallback(){
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            binding.chart.apply {
                data = viewModel.graphSeries.get()
                notifyDataSetChanged()
                invalidate()
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.graphSeries.addOnPropertyChangedCallback(graphListener)
        binding.chart.apply {

            description.text = "spacex rockets"
            val formatter: (Float, AxisBase) -> String = { value, _ ->  value.toInt().toString() }
            val xAxis = xAxis
            val yAxisLeft = axisLeft
            val yAxisRight = axisRight
            xAxis.setValueFormatter(formatter)
            yAxisLeft.setValueFormatter(formatter)
            yAxisRight.setValueFormatter(formatter)
            xAxis.granularity = 1f
            yAxisLeft.granularity = 1f
            yAxisRight.granularity = 1f

        }
        val linearLayoutManager = LinearLayoutManager(this.context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        binding.itemsRecycler.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
//            addItemDecoration(
//                VerticalItemDecoration(
//                    resources.getDimension(R.dimen.content_padding).toInt()
//                )
//            )
//            addItemDecoration(
//                HeaderItemDecoration()
//            )
            adapter = viewModel.launchesAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.graphSeries.removeOnPropertyChangedCallback(graphListener)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.launchmenu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh_launches -> {
                viewModel.updateLaunches()
            }
            android.R.id.home -> viewModel.backPressed()
        }

        return true
    }
}