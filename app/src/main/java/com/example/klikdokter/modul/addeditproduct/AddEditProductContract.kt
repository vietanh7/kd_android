package com.example.klikdokter.modul.addeditproduct

import com.example.base.presenter.MvpPresenter
import com.example.base.view.MvpView
import com.example.klikdokter.domain.entity.product.Product

interface AddEditProductContract {

    interface View : MvpView {
        fun onSubmitProductSuccess()
    }

    interface Presenter : MvpPresenter<View> {
        fun addProduct(product: Product)
        fun updateProduct(product: Product)
    }
}