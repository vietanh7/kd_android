package com.example.base.error

import com.example.base.view.CanShowError
import com.example.klikdokter.domain.logger.Logger
import java.lang.ref.WeakReference
import javax.inject.Inject

class DefaultErrorHandler @Inject constructor(private val logger: Logger) : ErrorHandler {
    private lateinit var viewRef: WeakReference<CanShowError>

    override fun proceed(error: Throwable) {
        logger.logError { error }
        val view = viewRef.get()
                ?: throw IllegalStateException("View must be attached to ErrorHandler")
        val message = error.message.orEmpty()
        view.showError(message)
    }

    override fun attachView(view: CanShowError) {
        viewRef = WeakReference(view)
    }

    override fun detachView() {
        viewRef.clear()
    }

}