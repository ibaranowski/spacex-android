package com.spacex.rockets.presentation.screens.rockets.list

import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.jakewharton.rxrelay2.PublishRelay
import com.spacex.rockets.R
import com.spacex.rockets.data.utils.EMPTY
import com.spacex.rockets.presentation.base.recycler.BaseItemViewModel
import com.spacex.rockets.presentation.base.recycler.ItemModel

class RocketItemViewModel(val clickSubject: PublishRelay<ItemModel<RocketItem>>) :
    BaseItemViewModel<RocketItem>() {

    private var item: RocketItem? = null
    private var position = 0

    val icon = ObservableInt()
    val title = ObservableField<String>(EMPTY)
    val color = ObservableInt()
    val description = ObservableField<String>(EMPTY)

    override fun setItem(item: RocketItem, position: Int) {
        this.item = item
        this.position = position
        color.set(if (item.active) R.color.app_green else R.color.app_red)
        title.set(item.name)
        description.set(item.country)
    }

    override fun onItemClick() {
        super.onItemClick()
        item?.also {
            clickSubject.accept(ItemModel(position, it))
        }
    }

}