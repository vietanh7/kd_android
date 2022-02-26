package com.example.klikdokter.modul.register

import com.example.base.presenter.MvpPresenter
import com.example.base.view.MvpView
import com.example.klikdokter.domain.entity.auth.Token

interface RegisterContract {

    interface View : MvpView {
        fun onRegisterSuccess(token: Token)
    }

    interface Presenter : MvpPresenter<View> {
        fun register(email: String, password: String)
    }
}