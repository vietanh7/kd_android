package com.example.klikdokter.modul.productlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.klikdokter.databinding.ItemProductBinding
import com.example.klikdokter.domain.entity.product.Product

class ProductAdapter(var products: List<Product>,
                     private val productListener: ProductListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(products[position], position)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    private inner class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product, position: Int) {
            with(binding) {
                tvSkuName.text = item.sku
                tvProductName.text = item.productName

                btnDelete.setOnClickListener {
                    productListener.onDeleteProduct(item.sku)
                }

                btnEdit.setOnClickListener {
                    productListener.onUpdateProduct(item)
                }
            }
        }
    }
}