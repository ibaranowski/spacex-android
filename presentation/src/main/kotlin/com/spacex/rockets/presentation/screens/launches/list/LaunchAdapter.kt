package com.spacex.rockets.presentation.screens.launches.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxrelay2.PublishRelay
import com.spacex.rockets.R
import com.spacex.rockets.presentation.base.recycler.BaseRecyclerViewAdapter
import com.spacex.rockets.presentation.base.recycler.HeaderItemDecoration
import com.spacex.rockets.presentation.base.recycler.ItemModel


class LaunchAdapter : BaseRecyclerViewAdapter<LaunchItem, LaunchItemViewModel>() {

    // HeaderItemDecoration.StickyHeaderInterface


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchItemViewHolder {
        return LaunchItemViewHolder.create(
            LayoutInflater.from(parent.context), parent,
            LaunchItemViewModel()
        )

    }

//    override fun getHeaderPositionForItem(itemPosition: Int): Int =
//        (itemPosition downTo 0)
//            .map { Pair(isHeader(it), it) }
//            .firstOrNull { it.first }?.second ?: RecyclerView.NO_POSITION
//
//    override fun getHeaderLayout(headerPosition: Int): Int {
//        return R.layout.header
//    }
//
//    override fun bindHeaderData(header: View, headerPosition: Int) {
//        if (headerPosition == RecyclerView.NO_POSITION) header.layoutParams.height = 0
//         /* ...else
//      here you get your header and can change some data on it
//    ... */
//    }
//
//    override fun isHeader(itemPosition: Int): Boolean {
//        return items[itemPosition].header
//    }

}