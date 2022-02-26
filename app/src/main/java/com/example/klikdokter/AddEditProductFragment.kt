package com.example.klikdokter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.extension.contentView
import com.example.base.view.BaseMvpVbFragment
import com.example.klikdokter.databinding.FragmentAddEditProductBinding
import com.example.klikdokter.domain.entity.product.Product
import com.example.klikdokter.helper.TSnackbarHelper
import com.example.klikdokter.modul.addeditproduct.AddEditProductContract
import com.example.klikdokter.navigation.ProductListKey
import com.zhuinden.simplestack.StateChange

class AddEditProductFragment: BaseMvpVbFragment<AddEditProductContract.Presenter,
        AddEditProductContract.View,
        FragmentAddEditProductBinding>(),
    AddEditProductContract.View{

    private var product: Product? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddEditProductBinding =
        FragmentAddEditProductBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindBundle()
        binding.btnSubmit.setOnClickListener { submitButtonHandler() }
    }

    private fun bindBundle() {
        product = arguments?.getParcelable(PRODUCT_KEY)
        val requiredProduct = product ?: return
        with(binding) {
            edtSku.setText(requiredProduct.sku)
            edtSku.isEnabled = false
            edtProductName.setText(requiredProduct.productName)
            edtPrice.setText(requiredProduct.price.toString())
            edtQuantity.setText(requiredProduct.qty.toString())
        }
    }

    private fun submitButtonHandler() {
        if (product != null) {
            updateProductHandler()
        } else {
            addProductHandler()
        }
    }

    private fun addProductHandler() = with(binding) {
        val sku = edtSku.text.toString()
        val productName = edtProductName.text.toString()
        val quantity = edtQuantity.text.toString().toIntOrNull() ?: 0
        val price = edtPrice.text.toString().toDoubleOrNull() ?: 0.0

        presenter.addProduct(Product(
            id = 0,
            sku = sku,
            productName = productName,
            qty = quantity,
            price = price,
            status = 1
        ))
    }

    private fun updateProductHandler() = with(binding) {
        val productName = edtProductName.text.toString()
        val quantity = edtQuantity.text.toString().toIntOrNull() ?: 0
        val price = edtPrice.text.toString().toDoubleOrNull() ?: 0.0

        presenter.updateProduct(Product(
            id = product?.id ?: 0,
            sku = product?.sku.orEmpty(),
            productName = productName,
            qty = quantity,
            price = price,
            status = 1
        ))
    }

    override fun onSubmitProductSuccess() {
        (activity as? MainActivity)?.replaceContent(ProductListKey(), StateChange.BACKWARD)
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

    companion object {
        private const val PRODUCT_KEY = "product_key"

        fun newInstance(product: Product): AddEditProductFragment = AddEditProductFragment().apply {
            arguments = Bundle().apply {
                putParcelable(PRODUCT_KEY, product)
            }
        }
    }
}