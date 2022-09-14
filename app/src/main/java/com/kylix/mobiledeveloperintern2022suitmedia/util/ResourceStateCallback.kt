package com.kylix.mobiledeveloperintern2022suitmedia.util

import android.view.View

abstract class ResourceStateCallback<T> {

    abstract fun onResourceLoading()
    abstract fun onResourceSuccess(data: T)

    open fun onResourceError(message: String) { }
    open fun onResourceError(message: String?, data: T?) { }

    open fun onResourceEmpty() { }
    open fun onNeverFetched() { }

    val visible
        get() = View.VISIBLE
    val invisible
        get() = View.INVISIBLE
    val gone
        get() = View.GONE

}