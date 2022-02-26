package com.example.klikdokter.modul.login

import com.example.base.presenter.MvpPresenter
import com.example.base.view.MvpView
import com.example.klikdokter.domain.entity.auth.Token

interface LoginContract {

    interface View : MvpView {
        fun onLoginSuccess(token: Token)
    }

    interface Presenter : MvpPresenter<View> {
        fun login(email: String, password: String)
    }
}