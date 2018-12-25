package com.spacex.rockets.presentation.screens.rockets

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.databinding.Observable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.spacex.rockets.R
import com.spacex.rockets.databinding.ActivityRocketsBinding
import com.spacex.rockets.presentation.base.BaseMvvmActivity
import com.spacex.rockets.presentation.base.ViewModelProviderFactory
import com.spacex.rockets.presentation.base.navigation.CustomNavigator
import com.spacex.rockets.presentation.base.recycler.VerticalItemDecoration
import com.spacex.rockets.presentation.utils.buildViewModel
import com.spacex.rockets.presentation.utils.setVisibility
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class RocketActivity : BaseMvvmActivity<ActivityRocketsBinding, RocketViewModel>(),
    HasSupportFragmentInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory<RocketViewModel>

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    override lateinit var navigator: CustomNavigator


    lateinit var menu: Menu

    companion object {

        fun getIntent(context: Context) = Intent(context, RocketActivity::class.java)
    }

    override fun supportFragmentInjector() = androidInjector

    override fun provideLayoutId(): Int = R.layout.activity_rockets

    override fun provideViewModel(): RocketViewModel =
        buildViewModel<RocketViewModel>(this, viewModelFactory)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val linearLayoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        binding.itemsRecycler.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            addItemDecoration(
                VerticalItemDecoration(
                    resources.getDimension(R.dimen.content_padding).toInt()
                )
            )
            adapter = viewModel.rocketAdapter
        }

        viewModel.apply {
            rocketAdapter.registerAdapterDataObserver(adapterDataListener)
            progressVisible.addOnPropertyChangedCallback(progressChangeListener)
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.updateRockets()
            menu.findItem(R.id.check_status).isChecked = false
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
//            it.setHomeButtonEnabled(true)
        }


    }

    private val adapterDataListener = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            binding.tvEmpty.setVisibility(viewModel.rocketAdapter.itemCount == 0)
        }
    }

    private val progressChangeListener = object : Observable.OnPropertyChangedCallback(){
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            val visible = viewModel.progressVisible.get()
            binding.swipeRefresh.isRefreshing = visible
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.rocketAdapter.unregisterAdapterDataObserver(adapterDataListener)
        viewModel.progressVisible.removeOnPropertyChangedCallback(progressChangeListener)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.mainmenu, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> {
                viewModel.updateRockets()
                menu.findItem(R.id.check_status).isChecked = false
            }
            R.id.check_status -> {
                val checked = !item.isChecked
                item.isChecked = checked
                viewModel.filterActive(checked)
            }
            android.R.id.home -> viewModel.backPressed()
        }

        return false
    }


}