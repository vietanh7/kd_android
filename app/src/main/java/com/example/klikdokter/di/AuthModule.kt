package com.example.klikdokter.di

import com.example.klikdokter.modul.login.LoginContract
import com.example.klikdokter.modul.login.LoginPresenter
import com.example.klikdokter.modul.register.RegisterContract
import com.example.klikdokter.modul.register.RegisterPresenter
import dagger.Binds
import dagger.Module

@Module
interface AuthModule {

    @Binds
    fun bindRegisterPresenter(presenter: RegisterPresenter): RegisterContract.Presenter

    @Binds
    fun bindLoginPresenter(presenter: LoginPresenter): LoginContract.Presenter
}