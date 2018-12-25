package com.spacex.rockets.presentation.utils

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class KeyboardActivityListener constructor(val activity: Activity)
    : ViewTreeObserver.OnGlobalLayoutListener {

    private var rootView: View = (activity.findViewById<View>(android.R.id.content) as ViewGroup)
    private var screenDensity: Float = activity.resources.displayMetrics.density

    private val stateSubject = BehaviorSubject.create<Boolean>()

    fun startListen() {
        rootView.viewTreeObserver.addOnGlobalLayoutListener(this)
    }

    fun stopListen() {
        rootView.viewTreeObserver.removeOnGlobalLayoutListener(this)
    }

    override fun onGlobalLayout() {
        val r = Rect()
        //r will be populated with the coordinates of your view that area still visible.
        rootView.getWindowVisibleDisplayFrame(r)

        val heightDiff = rootView.rootView.height - (r.bottom - r.top)
        val dp = heightDiff / screenDensity

        val state  = dp > 200

        if(state != stateSubject.value) {
            stateSubject.onNext(state)
        }
    }

    fun observeState(): Observable<Boolean> = stateSubject
}