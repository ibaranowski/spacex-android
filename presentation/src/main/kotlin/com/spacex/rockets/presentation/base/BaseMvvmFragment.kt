package com.spacex.rockets.presentation.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spacex.rockets.BR

abstract class BaseMvvmFragment<Binding : ViewDataBinding, ViewModel : BaseViewModel>
    : BaseFragment() {

    lateinit var binding: Binding
    lateinit var viewModel: ViewModel

    @LayoutRes abstract fun provideLayoutId(): Int
    abstract fun provideViewModel(): ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<Binding>(inflater, provideLayoutId(), container, false)
        binding.setVariable(BR.viewModel, viewModel)
        this.binding = binding
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }
}