package com.spacex.rockets.presentation.screens.launches.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.spacex.rockets.databinding.ItemLaunchBinding
import com.spacex.rockets.databinding.ItemRocketBinding
import com.spacex.rockets.presentation.base.recycler.BaseItemViewHolder

class LaunchItemViewHolder(binding: ItemLaunchBinding, viewModel: LaunchItemViewModel)
    : BaseItemViewHolder<LaunchItem, LaunchItemViewModel, ItemLaunchBinding>(binding, viewModel) {

    companion object {
        fun create(inflater: LayoutInflater, parent: ViewGroup?, viewModel: LaunchItemViewModel): LaunchItemViewHolder {
            val binding = ItemLaunchBinding.inflate(inflater, parent, false)
            return LaunchItemViewHolder(binding, viewModel)
        }
    }
}