package com.example.klikdokter.domain.schedulers

import io.reactivex.rxjava3.core.*
import javax.inject.Inject

class SchedulerTransformersImpl @Inject constructor(
    private val ioScheduler: Scheduler,
    private val mainScheduler: Scheduler,
    private val computationScheduler: Scheduler
): SchedulerTransformers {

    override fun <T> applySingleIoSchedulers(): SingleTransformer<T, T> =
        SingleTransformer { upstream ->
            upstream.subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
        }

    override fun <T> applySingleComputationSchedulers(): SingleTransformer<T, T> =
        SingleTransformer { upstream ->
            upstream.subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
        }

    override fun <T> applyMaybeIoSchedulers(): MaybeTransformer<T, T> =
        MaybeTransformer { upstream ->
            upstream.subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
        }

    override fun <T> applyFlowableIoSchedulers(): FlowableTransformer<T, T> =
        FlowableTransformer { upstream ->
            upstream.subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
        }

    override fun <T> applyFlowableComputationSchedulers(): FlowableTransformer<T, T> =
        FlowableTransformer { upstream ->
            upstream.subscribeOn(computationScheduler)
                .observeOn(mainScheduler)
        }

    override fun <T> applyObservableIoSchedulers(): ObservableTransformer<T, T> =
        ObservableTransformer { upstream ->
            upstream.subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
        }

    override fun applyCompletableIoSchedulers(): CompletableTransformer =
        CompletableTransformer { upstream ->
            upstream.subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
        }
}