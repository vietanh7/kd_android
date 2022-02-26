package com.example.klikdokter.di

import com.example.klikdokter.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [AuthModule::class])
    abstract fun registerFragment(): RegisterFragment

    @ContributesAndroidInjector(modules = [AuthModule::class])
    abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector(modules = [ProductModule::class])
    abstract fun productListFragment(): ProductListFragment

    @ContributesAndroidInjector(modules = [ProductModule::class])
    abstract fun addEditProductFragment(): AddEditProductFragment
}
