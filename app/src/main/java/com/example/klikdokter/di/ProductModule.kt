package com.example.klikdokter.di

import com.example.klikdokter.modul.addeditproduct.AddEditProductContract
import com.example.klikdokter.modul.addeditproduct.AddEditProductPresenter
import com.example.klikdokter.modul.productlist.ProductListContract
import com.example.klikdokter.modul.productlist.ProductListPresenter
import dagger.Binds
import dagger.Module

@Module
interface ProductModule {

    @Binds
    fun bindProductListPresenter(presenter: ProductListPresenter): ProductListContract.Presenter

    @Binds
    fun bindAddEditProductPresenter(presenter: AddEditProductPresenter): AddEditProductContract.Presenter
}