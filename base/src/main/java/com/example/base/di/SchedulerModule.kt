package com.example.base.di

import com.example.klikdokter.domain.schedulers.RunOn
import com.example.klikdokter.domain.schedulers.SchedulerTransformers
import com.example.klikdokter.domain.schedulers.SchedulerTransformersImpl
import com.example.klikdokter.domain.schedulers.SchedulerType
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

@Module
class SchedulerModule {
    @Provides
    @RunOn(SchedulerType.IO)
    fun provideIo(): Scheduler = Schedulers.io()

    @Provides
    @RunOn(SchedulerType.COMPUTATION)
    fun provideComputation(): Scheduler = Schedulers.computation()

    @Provides
    @RunOn(SchedulerType.UI)
    fun provideUi(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    fun provideSchedulerTransformers(
            @RunOn(SchedulerType.UI) uiScheduler: Scheduler,
            @RunOn(SchedulerType.IO) ioScheduler: Scheduler,
            @RunOn(SchedulerType.COMPUTATION) computationScheduler: Scheduler
    ): SchedulerTransformers = SchedulerTransformersImpl(ioScheduler, uiScheduler, computationScheduler)
}