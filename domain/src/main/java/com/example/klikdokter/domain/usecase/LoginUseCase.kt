package com.example.klikdokter.domain.usecase

import com.example.klikdokter.domain.entity.auth.Token
import com.example.klikdokter.domain.logger.Logger
import com.example.klikdokter.domain.repository.AuthRepository
import com.example.klikdokter.domain.schedulers.SchedulerTransformers
import com.example.klikdokter.domain.usecase.base.SingleUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    schedulerTransformer: SchedulerTransformers? = null,
    logger: Logger? = null
) : SingleUseCase<Token, LoginUseCase.Params>(schedulerTransformer?.applySingleIoSchedulers(), logger) {

    override fun build(params: Params?): Single<Token> = params!!.run {
        authRepository.login(email, password)
    }

    data class Params(
        val email: String,
        val password: String
    )
}