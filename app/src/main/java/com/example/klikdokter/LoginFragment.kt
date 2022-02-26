package com.example.klikdokter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.extension.contentView
import com.example.base.view.BaseMvpVbFragment
import com.example.klikdokter.databinding.FragmentLoginBinding
import com.example.klikdokter.domain.entity.auth.Token
import com.example.klikdokter.domain.section.SessionPreference
import com.example.klikdokter.helper.TSnackbarHelper
import com.example.klikdokter.modul.login.LoginContract
import com.example.klikdokter.navigation.HomeKey
import com.example.klikdokter.navigation.ProductListKey
import com.zhuinden.simplestack.StateChange
import javax.inject.Inject

class LoginFragment: BaseMvpVbFragment<LoginContract.Presenter,
        LoginContract.View,
        FragmentLoginBinding>(),
    LoginContract.View{

    @Inject
    lateinit var sessionPreference: SessionPreference

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding =
        FragmentLoginBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener { loginHandler() }
    }

    private fun loginHandler() = with(binding) {
        val email = edtEmail.text.toString()
        val password = edtPassword.text.toString()
        presenter.login(email, password)
    }

    override fun onLoginSuccess(token: Token) {
        sessionPreference.setToken(token.token)
        (activity as? MainActivity)?.replaceContent(ProductListKey(), StateChange.FORWARD)
    }

    override fun showLoading() {
        showDialogLoading()
    }

    override fun hideLoading() {
        hideDialogLoading()
    }

    override fun showError(message: String) {
        activity?.contentView()?.run {
            TSnackbarHelper.error(this, message)
        }
    }
}