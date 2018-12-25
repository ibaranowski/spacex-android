package com.spacex.rockets.presentation.base.recycler

data class ItemModel<out Model>(
        val position: Int,
        val model: Model)