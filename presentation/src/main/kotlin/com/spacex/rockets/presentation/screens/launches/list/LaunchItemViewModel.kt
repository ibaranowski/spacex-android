package com.spacex.rockets.presentation.screens.launches.list

import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.spacex.rockets.R
import com.spacex.rockets.data.utils.EMPTY
import com.spacex.rockets.presentation.base.recycler.BaseItemViewModel
import com.spacex.rockets.presentation.utils.PATTERN_COMMON
import com.spacex.rockets.presentation.utils.formatDate

class LaunchItemViewModel :
    BaseItemViewModel<LaunchItem>() {

    private var item: LaunchItem? = null
    private var position = 0

    val missionImage = ObservableField<String>(EMPTY)
    val launchDate = ObservableField<String>(EMPTY)
    val missionName = ObservableField<String>(EMPTY)
    val year = ObservableField<String>(EMPTY)
    val color = ObservableInt()
    val header = ObservableField<Boolean>()

    override fun setItem(item: LaunchItem, position: Int) {
        this.item = item
        this.position = position
        missionName.set(item.missionName)
        launchDate.set(formatDate(item.launchDate, PATTERN_COMMON))
        color.set(if (item.launchSuccess) R.color.app_green else R.color.app_red)
        year.set(item.launchYear.toString())
        missionImage.set(item.missionPatchImage)
        header.set(item.header)
    }


}