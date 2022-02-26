package com.example.klikdokter.domain.usecase.base

import com.example.klikdokter.domain.logger.Logger

abstract class UseCase<Response, in Params>(private val logger: Logger?) {

    protected abstract fun build(params: Params?): Response

    open fun execute(params: Params? = null): Response {
        logger?.log { "${javaClass.simpleName} : $params" }
        return build(params)
    }

}