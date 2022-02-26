package com.example.base.view

interface MvpView: CanShowError {
    fun showLoading()
    fun hideLoading()
}