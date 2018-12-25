package com.spacex.rockets.presentation.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment: Fragment() {

    private var baseActivity: BaseActivity? = null

    protected val disposables: CompositeDisposable by lazy { CompositeDisposable() }

//    abstract fun getFragmentTag(): String

    public fun getBaseActivity() : BaseActivity? = baseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.baseActivity = baseActivity
        }
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    fun hideKeyboard() = baseActivity?.hideKeyboard()

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}