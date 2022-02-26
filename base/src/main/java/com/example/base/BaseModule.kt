package com.example.base

import com.example.base.error.AppErrorHandler
import com.example.base.error.DefaultErrorHandler
import com.example.base.error.ErrorHandler
import com.example.base.error.ErrorHandlingNavigation
import com.example.klikdokter.data.logger.TimberLogger
import com.example.klikdokter.domain.logger.Logger
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
interface BaseModule {

    @Binds
    fun bindLogger(timberLogger: TimberLogger): Logger

    @Binds
    @Named(Constants.ERROR_HANDLER_DEFAULT)
    fun bindDefaultErrorHandler(errorHandler: DefaultErrorHandler): ErrorHandler

    @Binds
    @Named(Constants.ERROR_HANDLER_APP)
    fun bindAppErrorHandler(errorHandler: AppErrorHandler): ErrorHandler
}