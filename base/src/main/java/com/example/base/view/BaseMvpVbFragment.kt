package com.example.base.view

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.base.presenter.MvpPresenter
import javax.inject.Inject

abstract class BaseMvpVbFragment<P : MvpPresenter<V>, V : MvpView, VB : ViewBinding> : BaseVbFragment<VB>() {

    @Inject
    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this as V)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }
}
