package com.example.klikdokter.modul.login

import com.example.base.presenter.BasePresenter
import com.example.klikdokter.domain.usecase.LoginUseCase
import io.reactivex.rxjava3.functions.Consumer
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    override fun login(email: String, password: String) {
        loginUseCase.execute(LoginUseCase.Params(email, password))
            .withState()
            .subscribe(Consumer { view?.onLoginSuccess(it) })
            .onPresenter()
    }

}