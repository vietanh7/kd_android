package com.example.klikdokter.modul.register

import com.example.base.presenter.BasePresenter
import com.example.klikdokter.domain.usecase.RegisterUseCase
import io.reactivex.rxjava3.functions.Consumer
import javax.inject.Inject

class RegisterPresenter @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : BasePresenter<RegisterContract.View>(), RegisterContract.Presenter {

    override fun register(email: String, password: String) {
        registerUseCase.execute(RegisterUseCase.Params(email, password))
            .withState()
            .subscribe(Consumer { view?.onRegisterSuccess(it) })
            .onPresenter()
    }

}