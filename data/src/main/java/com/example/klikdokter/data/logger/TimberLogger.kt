package com.example.klikdokter.data.logger

import com.example.klikdokter.domain.logger.Logger
import timber.log.Timber
import javax.inject.Inject

class TimberLogger @Inject constructor() : Logger {

    override fun log(message: () -> String) {
        Timber.d(message.invoke())
    }

    override fun logError(throwable: () -> Throwable) {
        Timber.e(throwable.invoke())
    }

}