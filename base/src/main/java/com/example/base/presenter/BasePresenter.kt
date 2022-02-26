package com.example.base.presenter

import com.example.base.Constants.ERROR_HANDLER_APP
import com.example.base.error.ErrorHandler
import com.example.base.view.MvpView
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Named


abstract class BasePresenter<View : MvpView> : MvpPresenter<View> {

    @Inject
    @Named(ERROR_HANDLER_APP)
    lateinit var errorHandler: ErrorHandler

    private var viewRef: WeakReference<View>? = null
    protected val composite: CompositeDisposable? = CompositeDisposable()

    override val view: View?
        get() = viewRef?.get()

    override fun attach(mvpView: View) {
        viewRef = WeakReference(mvpView)
        errorHandler.attachView(mvpView)
    }

    override fun detach() {
        clear()
        errorHandler.detachView()
    }

    fun clear() {
        composite?.clear()
        viewRef?.clear()
        viewRef = null
    }

    fun Disposable.onPresenter() {
        composite?.add(this)
    }

    fun <T> Single<T>.withState(): Single<T> = this
        .doOnSubscribe { view?.showLoading() }
        .doAfterTerminate { view?.hideLoading() }
        .doOnError { errorHandler.proceed(it) }

    fun <T> Maybe<T>.withState(): Maybe<T> = this
        .doOnSubscribe { view?.showLoading() }
        .doAfterTerminate { view?.hideLoading() }
        .doOnError { errorHandler.proceed(it) }

    fun <T> Flowable<T>.withState(): Flowable<T> = this
        .doOnSubscribe { view?.showLoading() }
        .doAfterNext { view?.hideLoading() }
        .doAfterTerminate { view?.hideLoading() }
        .doOnError { errorHandler.proceed(it) }

    fun <T> Observable<T>.withState(): Observable<T> = this
        .doOnSubscribe { view?.showLoading() }
        .doAfterNext { view?.hideLoading() }
        .doAfterTerminate { view?.hideLoading() }
        .doOnError { errorHandler.proceed(it) }

    fun Completable.withState(): Completable = this
        .doOnSubscribe { view?.showLoading() }
        .doAfterTerminate { view?.hideLoading() }
        .doOnError { errorHandler.proceed(it) }

}