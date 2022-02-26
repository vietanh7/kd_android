package com.example.klikdokter.modul.addeditproduct

import com.example.base.presenter.BasePresenter
import com.example.klikdokter.domain.entity.product.Product
import com.example.klikdokter.domain.usecase.AddProductUseCase
import com.example.klikdokter.domain.usecase.UpdateProductUseCase
import io.reactivex.rxjava3.functions.Consumer
import javax.inject.Inject

class AddEditProductPresenter @Inject constructor(
    private val addProductUseCase: AddProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
) : BasePresenter<AddEditProductContract.View>(), AddEditProductContract.Presenter {

    override fun addProduct(product: Product) {
        addProductUseCase.execute(product)
            .withState()
            .subscribe(Consumer { view?.onSubmitProductSuccess() })
            .onPresenter()
    }

    override fun updateProduct(product: Product) {
        updateProductUseCase.execute(product)
            .withState()
            .subscribe(Consumer { view?.onSubmitProductSuccess() })
            .onPresenter()
    }
}