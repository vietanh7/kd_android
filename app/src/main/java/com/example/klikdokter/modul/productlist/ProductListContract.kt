package com.example.klikdokter.modul.productlist

import com.example.base.presenter.MvpPresenter
import com.example.base.view.MvpView
import com.example.klikdokter.domain.entity.product.Product

interface ProductListContract {

    interface View : MvpView {
        fun onGetProductListSuccess(productList: List<Product>)
        fun onSearchSuccess(productList: List<Product>)
        fun onDeleteProductSuccess()
    }

    interface Presenter : MvpPresenter<View> {
        fun getProductList()
        fun deleteProduct(sku: String)
        fun searchProduct(sku: String)
    }
}