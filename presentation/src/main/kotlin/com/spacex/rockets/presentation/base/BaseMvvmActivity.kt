package com.spacex.rockets.presentation.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import com.spacex.rockets.BR

abstract class BaseMvvmActivity<Binding : ViewDataBinding, ViewModel : BaseViewModel>
    : BaseActivity() {

    lateinit var binding: Binding
    lateinit var viewModel: ViewModel

    @LayoutRes abstract fun provideLayoutId(): Int
    abstract fun provideViewModel(): ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<Binding>(this, provideLayoutId())
        viewModel = provideViewModel()
        binding.setVariable(BR.viewModel, viewModel)
        this.binding = binding
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