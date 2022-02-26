package com.example.klikdokter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.extension.contentView
import com.example.base.view.BaseMvpVbFragment
import com.example.klikdokter.databinding.FragmentProductListBinding
import com.example.klikdokter.domain.entity.product.Product
import com.example.klikdokter.domain.section.SessionPreference
import com.example.klikdokter.helper.TSnackbarHelper
import com.example.klikdokter.modul.productlist.ProductAdapter
import com.example.klikdokter.modul.productlist.ProductListContract
import com.example.klikdokter.modul.productlist.ProductListener
import com.example.klikdokter.navigation.AddEditProductKey
import com.example.klikdokter.navigation.HomeKey
import com.jakewharton.rxbinding4.appcompat.queryTextChanges
import com.zhuinden.simplestack.StateChange
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductListFragment: BaseMvpVbFragment<ProductListContract.Presenter,
        ProductListContract.View,
        FragmentProductListBinding>(),
        ProductListContract.View,
        ProductListener
{

    @Inject
    lateinit var sessionPreference: SessionPreference

    private lateinit var productAdapter: ProductAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProductListBinding =
        FragmentProductListBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnLogout.setOnClickListener { logoutHandler() }
            btnAddProduct.setOnClickListener { addProductHandler() }
        }

        setupSwipeRefresh()
        setupRecyclerView()
        handleInputEvent()
        fetchProducts()
    }

    private fun handleInputEvent() {
        binding.edtSearch.queryTextChanges()
            .skipInitialValue()
            .doOnNext { onQueryChanged() }
            .debounce(300, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::search)
    }

    private fun onQueryChanged() {
        binding.srlProduct.isRefreshing = true
    }

    private fun search(query: CharSequence) {
        loadSearchTaskList(query.toString())
    }

    private fun loadSearchTaskList(searchTerm: String) {
        presenter.searchProduct(searchTerm)
    }

    private fun logoutHandler() {
        sessionPreference.setToken("")
        (activity as? MainActivity)?.replaceContent(HomeKey(), StateChange.BACKWARD)
    }

    private fun addProductHandler() {
        (activity as? MainActivity)?.replaceContent(AddEditProductKey(), StateChange.FORWARD)
    }

    private fun setupRecyclerView() = with(binding.rvProduct) {
        productAdapter = ProductAdapter(mutableListOf(), this@ProductListFragment)
        val linearLayoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = RecyclerView.VERTICAL
        }
        layoutManager = linearLayoutManager
        adapter = productAdapter
    }

    private fun setupSwipeRefresh() {
        binding.srlProduct.setOnRefreshListener {
            binding.edtSearch.setQuery("", false)
            fetchProducts()
        }
    }

    private fun fetchProducts() {
        presenter.getProductList()
    }

    private fun bindProductList(productList: List<Product>) {
        binding.srlProduct.isRefreshing = false
        productAdapter.products = productList
        productAdapter.notifyDataSetChanged()
    }

    override fun onGetProductListSuccess(productList: List<Product>) {
        bindProductList(productList)
    }

    override fun onSearchSuccess(productList: List<Product>) {
        bindProductList(productList)
    }

    override fun onDeleteProductSuccess() {
        fetchProducts()
    }

    override fun onDeleteProduct(sku: String) {
        presenter.deleteProduct(sku)
    }

    override fun onUpdateProduct(product: Product) {
        (activity as? MainActivity)?.replaceContent(AddEditProductKey(product = product), StateChange.FORWARD)
    }

    override fun showLoading() {
        showDialogLoading()
    }

    override fun hideLoading() {
        hideDialogLoading()
    }

    override fun showError(message: String) {
        binding.srlProduct.isRefreshing = false
        activity?.contentView()?.run {
            TSnackbarHelper.error(this, message)
        }
    }
}