package com.example.klikdokter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.extension.contentView
import com.example.base.view.BaseMvpVbFragment
import com.example.klikdokter.databinding.FragmentRegisterBinding
import com.example.klikdokter.domain.entity.auth.Token
import com.example.klikdokter.helper.TSnackbarHelper
import com.example.klikdokter.modul.register.RegisterContract
import com.example.klikdokter.navigation.HomeKey
import com.zhuinden.simplestack.StateChange

class RegisterFragment: BaseMvpVbFragment<RegisterContract.Presenter,
        RegisterContract.View,
        FragmentRegisterBinding>(),
    RegisterContract.View{

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding =
        FragmentRegisterBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener { registerHandler() }
    }

    private fun registerHandler() = with(binding) {
        val email = edtEmail.text.toString()
        val password =edtPassword.text.toString()
        presenter.register(email, password)
    }

    override fun onRegisterSuccess(token: Token) {
        (activity as? MainActivity)?.replaceContent(HomeKey(), StateChange.BACKWARD)
        activity?.contentView()?.run {
            TSnackbarHelper.success(this, getString(R.string.message_register_success))
        }
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