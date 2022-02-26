package com.example.klikdokter.di

import com.example.base.BaseModule
import com.example.base.di.SchedulerModule
import com.example.klikdokter.BaseApplication
import com.example.klikdokter.data.di.ApiModule
import com.example.klikdokter.data.di.NetworkModule
import com.example.klikdokter.data.di.RepositoryModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppActivityBindingModule::class,
        // data
        AppModule::class,
        AppDataModule::class,
        ApiModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        // base
        BaseModule::class,
        SchedulerModule::class,
        NavigationModule::class
    ]
)
@Singleton
interface MainComponent : AndroidInjector<BaseApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<BaseApplication>
}