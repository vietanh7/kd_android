package com.example.klikdokter.domain.usecase

import com.example.klikdokter.domain.entity.product.Product
import com.example.klikdokter.domain.logger.Logger
import com.example.klikdokter.domain.repository.ProductRepository
import com.example.klikdokter.domain.schedulers.SchedulerTransformers
import com.example.klikdokter.domain.usecase.base.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchProductUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    schedulerTransformer: SchedulerTransformers? = null,
    logger: Logger? = null
) : SingleUseCase<Product, String>(schedulerTransformer?.applySingleIoSchedulers(), logger) {


    override fun build(params: String?): Single<Product> =
        productRepository.searchProduct(params.orEmpty())
}