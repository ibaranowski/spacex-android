package com.spacex.rockets.presentation.base.recycler

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class VerticalItemDecoration(private val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        val adapter = parent.adapter
        adapter?.also {
            if (parent.getChildAdapterPosition(view) < it.itemCount) {
                outRect.bottom = verticalSpaceHeight
            }
        }

    }
}