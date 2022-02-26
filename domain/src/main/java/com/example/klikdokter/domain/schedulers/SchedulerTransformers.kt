package com.example.klikdokter.domain.schedulers

import io.reactivex.rxjava3.core.*

interface SchedulerTransformers {

    fun <T> applySingleIoSchedulers(): SingleTransformer<T, T>

    fun <T> applySingleComputationSchedulers(): SingleTransformer<T, T>

    fun <T> applyMaybeIoSchedulers(): MaybeTransformer<T, T>

    fun <T> applyFlowableIoSchedulers(): FlowableTransformer<T, T>

    fun <T> applyFlowableComputationSchedulers(): FlowableTransformer<T, T>

    fun <T> applyObservableIoSchedulers(): ObservableTransformer<T, T>

    fun applyCompletableIoSchedulers(): CompletableTransformer
}