package com.example.klikdokter.di

import android.content.Context
import com.example.klikdokter.BaseApplication
import com.example.klikdokter.domain.section.SessionPreference
import com.example.klikdokter.domain.section.SessionPreferenceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    abstract fun bindApplicationContext(app: BaseApplication): Context

    @Binds
    @Singleton
    abstract fun bindSessionPreference(sessionPreferenceImpl: SessionPreferenceImpl): SessionPreference


}