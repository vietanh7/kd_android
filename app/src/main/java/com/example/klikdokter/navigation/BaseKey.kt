package com.example.klikdokter.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

abstract class BaseKey : Parcelable {
    var identifier: String = "0"
    val fragmentTag: String
        get() = toString()

    fun newFragment(): Fragment = createFragment().apply {
        arguments = (arguments ?: Bundle()).also { bundle ->
            bundle.putParcelable("KEY", this@BaseKey)
        }
    }

    protected abstract fun createFragment(): Fragment

    @StringRes
    abstract fun getFragmentTitleRes(): Int
}
