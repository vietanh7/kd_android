package com.example.klikdokter.navigation

import com.example.klikdokter.*
import com.example.klikdokter.domain.entity.product.Product
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeKey(private val placeholder: String = "") : BaseKey() {
    override fun createFragment() = HomeFragment()
    override fun getFragmentTitleRes(): Int = R.string.home
}

@Parcelize
data class RegisterKey(private val placeholder: String = "") : BaseKey() {
    override fun createFragment() = RegisterFragment()
    override fun getFragmentTitleRes(): Int = R.string.register
}

@Parcelize
data class LoginKey(private val placeholder: String = "") : BaseKey() {
    override fun createFragment() = LoginFragment()
    override fun getFragmentTitleRes(): Int = R.string.login
}

@Parcelize
data class ProductListKey(private val placeholder: String = "") : BaseKey() {
    override fun createFragment() = ProductListFragment()
    override fun getFragmentTitleRes(): Int = R.string.products
}

@Parcelize
data class AddEditProductKey(private val placeholder: String = "", val product: Product? = null) : BaseKey() {
    override fun createFragment() = if (product == null) {
        AddEditProductFragment()
    } else {
        AddEditProductFragment.newInstance(product)
    }
    override fun getFragmentTitleRes(): Int = R.string.add_product
}