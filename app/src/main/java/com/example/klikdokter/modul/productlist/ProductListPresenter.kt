package com.example.klikdokter.modul.productlist

import com.example.base.presenter.BasePresenter
import com.example.klikdokter.domain.usecase.DeleteProductUseCase
import com.example.klikdokter.domain.usecase.GetProductListUseCase
import com.example.klikdokter.domain.usecase.SearchProductUseCase
import io.reactivex.rxjava3.functions.Consumer
import javax.inject.Inject

class ProductListPresenter @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val searchProductUseCase: SearchProductUseCase,
) : BasePresenter<ProductListContract.View>(), ProductListContract.Presenter {

    override fun getProductList() {
        getProductListUseCase.execute()
            .withState()
            .subscribe(Consumer { view?.onGetProductListSuccess(it) })
            .onPresenter()
    }

    override fun deleteProduct(sku: String) {
        deleteProductUseCase.execute(sku)
            .withState()
            .subscribe(Consumer { view?.onDeleteProductSuccess() })
            .onPresenter()
    }

    override fun searchProduct(sku: String) {
        searchProductUseCase.execute(sku)
            .subscribe(Consumer { product ->
                if (product.sku.isEmpty())
                    view?.onSearchSuccess(mutableListOf())
                else
                    view?.onSearchSuccess(mutableListOf(product))
            })
            .onPresenter()
    }
}