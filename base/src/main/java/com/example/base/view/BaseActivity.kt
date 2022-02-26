package com.example.base.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity() : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private var resourceLayout: Int? = null

    constructor(@LayoutRes resourceLayout: Int) : this() {
        this.resourceLayout = resourceLayout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        resourceLayout?.let { setContentView(it) }
    }

    override fun androidInjector(): AndroidInjector<Any?>? {
        return androidInjector
    }

    private fun getActivityName(): String = this.javaClass.simpleName
}