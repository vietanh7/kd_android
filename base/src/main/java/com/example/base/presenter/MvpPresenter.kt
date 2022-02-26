package com.example.base.presenter

import com.example.base.view.MvpView

interface MvpPresenter<V : MvpView> {
    val view: V?

    fun attach(mvpView: V)
    fun detach()
    fun isViewBound() = view != null
}