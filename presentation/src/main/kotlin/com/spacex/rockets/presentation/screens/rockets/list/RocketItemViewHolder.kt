package com.spacex.rockets.presentation.screens.rockets.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.spacex.rockets.databinding.ItemRocketBinding
import com.spacex.rockets.presentation.base.recycler.BaseItemViewHolder

class RocketItemViewHolder(binding: ItemRocketBinding, viewModel: RocketItemViewModel)
    : BaseItemViewHolder<RocketItem, RocketItemViewModel, ItemRocketBinding>(binding, viewModel) {

    companion object {
        fun create(inflater: LayoutInflater, parent: ViewGroup?, viewModel: RocketItemViewModel): RocketItemViewHolder {
            val binding = ItemRocketBinding.inflate(inflater, parent, false)
            return RocketItemViewHolder(binding, viewModel)
        }
    }
}