package com.spacex.rockets.presentation.base.recycler

abstract class BaseItemViewModel<in Model> {

    abstract fun setItem(item: Model, position: Int)

    open fun onAttached() {
    }

    open fun onDetached() {
    }

    open fun onItemClick() {
    }
}