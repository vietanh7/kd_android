package com.example.klikdokter

import android.app.Activity
import android.os.Bundle
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.example.klikdokter.di.DaggerMainComponent
import com.example.klikdokter.domain.logger.Logger
import java.io.IOException
import java.net.SocketException
import javax.inject.Inject
import io.reactivex.exceptions.UndeliverableException as RxUndeliverableException2
import io.reactivex.plugins.RxJavaPlugins as RxPlugins2
import io.reactivex.rxjava3.exceptions.UndeliverableException as RxUndeliverableException3
import io.reactivex.rxjava3.plugins.RxJavaPlugins as RxPlugins3

class BaseApplication : DaggerApplication() {

    @Inject
    lateinit var logger: Logger

    var topActivity: Activity? = null

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerMainComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()

        setRxJavaErrorHandler()
        registerActivityLifecycle()
    }

    private fun registerActivityLifecycle() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
                topActivity = activity
            }

            override fun onActivityResumed(activity: Activity) {
                topActivity = activity
            }

            override fun onActivityStarted(activity: Activity) {
                topActivity = activity
            }

            override fun onActivityDestroyed(activity: Activity) {
                // Do nothing
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                // Do nothing
            }

            override fun onActivityStopped(activity: Activity) {
                // Do nothing
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                // Do nothing
            }
        })
    }

    private fun setRxJavaErrorHandler() {
        RxPlugins2.setErrorHandler { exception ->
            val e: Throwable? = if (exception is RxUndeliverableException2) {
                exception.cause
            } else {
                exception
            }

            when (e) {
                is IOException, is SocketException -> {
                    // fine, irrelevant network problem or API that throws on cancellation
                    return@setErrorHandler
                }
                is InterruptedException -> {
                    // fine, some blocking code was interrupted by a dispose call
                    return@setErrorHandler
                }
                is NullPointerException, is IllegalArgumentException -> {
                    // that's likely a bug in the application
                    Thread.currentThread()
                        .uncaughtExceptionHandler?.uncaughtException(Thread.currentThread(), e)
                    return@setErrorHandler
                }
                is IllegalStateException -> {
                    // that's a bug in RxJava or in a custom operator
                    Thread.currentThread()
                        .uncaughtExceptionHandler?.uncaughtException(Thread.currentThread(), e)
                    return@setErrorHandler
                }
            }
            logger.logError { exception }
        }

        RxPlugins3.setErrorHandler { exception ->
            val e: Throwable? = if (exception is RxUndeliverableException3) {
                exception.cause
            } else {
                exception
            }

            when (e) {
                is IOException, is SocketException -> {
                    // fine, irrelevant network problem or API that throws on cancellation
                    return@setErrorHandler
                }
                is InterruptedException -> {
                    // fine, some blocking code was interrupted by a dispose call
                    return@setErrorHandler
                }
                is NullPointerException, is IllegalArgumentException -> {
                    // that's likely a bug in the application
                    Thread.currentThread()
                        .uncaughtExceptionHandler?.uncaughtException(Thread.currentThread(), e)
                    return@setErrorHandler
                }
                is IllegalStateException -> {
                    // that's a bug in RxJava or in a custom operator
                    Thread.currentThread()
                        .uncaughtExceptionHandler?.uncaughtException(Thread.currentThread(), e)
                    return@setErrorHandler
                }
            }
            logger.logError { exception }
        }
    }
}