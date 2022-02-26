package com.example.base.error

import android.accounts.NetworkErrorException
import android.content.Context
import com.example.base.Constants.ERROR_HANDLER_DEFAULT
import com.example.base.R
import com.example.base.view.CanShowError
import com.example.klikdokter.data.service.intercept.AppRestException
import java.net.*
import javax.inject.Inject
import javax.inject.Named

class AppErrorHandler @Inject constructor(
    private val context: Context,
    @Named(ERROR_HANDLER_DEFAULT) private val defaultErrorHandler: ErrorHandler,
    private val errorHandlingNavigation: ErrorHandlingNavigation,
) : ErrorHandler {
    override fun proceed(error: Throwable) {
        when {
            error is AppRestException -> {
                when (error.code) {
                    HttpURLConnection.HTTP_BAD_REQUEST, HttpURLConnection.HTTP_UNAUTHORIZED -> {
                        errorHandlingNavigation.onUserTokenInvalid()
                        return
                    }
                    else -> {
                        defaultErrorHandler.proceed(error)
                        return
                    }
                }
            }
            error is SocketTimeoutException -> {
                defaultErrorHandler.proceed(
                    AppRestException(
                        HttpURLConnection.HTTP_GATEWAY_TIMEOUT,
                        message = context.getString(R.string.error_request_timeout)
                    )
                )
                return
            }
            isConnectionException(error) -> {
                defaultErrorHandler.proceed(
                    AppRestException(
                        HttpURLConnection.HTTP_BAD_GATEWAY,
                        message = context.getString(R.string.error_no_connection)
                    )
                )
                return
            }
            else -> {
                defaultErrorHandler.showGeneralMessageError(error)
            }
        }
    }

    private fun ErrorHandler.showGeneralMessageError(error: Throwable) {
        proceed(AppRestException(
            error.hashCode(),
            message = context.getString(R.string.error_general_message)
        ))
    }

    private fun isConnectionException(error: Throwable) =
        when (error) {
            is ConnectException,
            is UnknownHostException,
            is NoRouteToHostException,
            is ProtocolException,
            is NetworkErrorException -> true
            else -> false
        }

    override fun attachView(view: CanShowError) {
        defaultErrorHandler.attachView(view)
    }

    override fun detachView() {
        defaultErrorHandler.detachView()
    }
}