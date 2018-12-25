package com.spacex.rockets.presentation.screens.rockets.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jakewharton.rxrelay2.PublishRelay
import com.spacex.rockets.presentation.base.recycler.BaseRecyclerViewAdapter
import com.spacex.rockets.presentation.base.recycler.ItemModel


class RocketAdapter : BaseRecyclerViewAdapter<RocketItem, RocketItemViewModel>() {

    val clickSubject: PublishRelay<ItemModel<RocketItem>> =
        PublishRelay.create<ItemModel<RocketItem>>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketItemViewHolder {
        return RocketItemViewHolder.create(
            LayoutInflater.from(parent.context), parent,
            RocketItemViewModel(clickSubject)
        )

    }

}