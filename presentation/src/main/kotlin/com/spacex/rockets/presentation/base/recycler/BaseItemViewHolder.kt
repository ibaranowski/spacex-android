package com.spacex.rockets.presentation.base.recycler

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.spacex.rockets.BR

open class BaseItemViewHolder<in Model,
        out ViewModel : BaseItemViewModel<Model>,
        out Binding : ViewDataBinding>(
        val binding: Binding,
        val viewModel: ViewModel)
    : RecyclerView.ViewHolder(binding.root) {

    init {
        initViewModel()
    }

    protected open fun initViewModel() {
        binding.setVariable(BR.viewModel, viewModel)
    }

    fun attach() {
        viewModel.onAttached()
    }

    fun bindTo(item: Model, position: Int) {
        viewModel.setItem(item, position)
        binding.executePendingBindings()
    }

    fun detach() {
        viewModel.onDetached()
    }
}