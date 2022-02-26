package com.example.klikdokter.di

import com.example.klikdokter.BuildConfig
import com.example.klikdokter.data.DataConstants
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppDataModule {

    @Provides
    @Singleton
    @Named(DataConstants.BASE_URL)
    fun provideBaseUrl(): String = BuildConfig.BASE_URL
}