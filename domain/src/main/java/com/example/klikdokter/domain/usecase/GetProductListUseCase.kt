package com.example.klikdokter.domain.usecase

import com.example.klikdokter.domain.entity.product.Product
import com.example.klikdokter.domain.logger.Logger
import com.example.klikdokter.domain.repository.ProductRepository
import com.example.klikdokter.domain.schedulers.SchedulerTransformers
import com.example.klikdokter.domain.usecase.base.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    schedulerTransformer: SchedulerTransformers? = null,
    logger: Logger? = null
) : SingleUseCase<List<Product>, Void>(schedulerTransformer?.applySingleIoSchedulers(), logger) {

    override fun build(params: Void?): Single<List<Product>> = productRepository.getProductList()
}