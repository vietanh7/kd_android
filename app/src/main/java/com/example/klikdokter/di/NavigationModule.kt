package com.example.klikdokter.di

import com.example.base.error.ErrorHandlingNavigation
import com.example.klikdokter.navigation.ErrorHandlingNavigationImpl
import dagger.Binds
import dagger.Module

@Module
interface NavigationModule {

    @Binds
    fun bindErrorHandlingNavigation(errorHandlingNavigationImpl: ErrorHandlingNavigationImpl): ErrorHandlingNavigation

}

