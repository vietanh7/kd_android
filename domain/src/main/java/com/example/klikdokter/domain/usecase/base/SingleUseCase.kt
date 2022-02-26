package com.example.klikdokter.domain.usecase.base

import com.example.klikdokter.domain.logger.Logger
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer

abstract class SingleUseCase<Response, in Params>
constructor(
    private val singleTransformer: SingleTransformer<Response, Response>?,
    private val logger: Logger?
) : UseCase<Single<Response>, Params>(logger) {

    override fun execute(params: Params?): Single<Response> = super.execute(params)
        .compose { transformer -> singleTransformer?.let { transformer.compose(it) } ?: transformer }
        .doOnError { logger?.logError { it } }
        .doOnSuccess { logger?.log { "${javaClass.simpleName} : $params => $it" } }

}