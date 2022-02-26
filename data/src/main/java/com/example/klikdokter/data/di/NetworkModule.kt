package com.example.klikdokter.data.di

import com.example.klikdokter.data.BuildConfig
import com.example.klikdokter.data.DataConstants
import com.example.klikdokter.data.DataConstants.BASE_URL
import com.example.klikdokter.data.DataConstants.RXJAVA3_CALL_ADAPTER
import com.example.klikdokter.data.service.intercept.MyRequestInterceptor
import com.example.klikdokter.data.service.intercept.MyResponseInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named(RXJAVA3_CALL_ADAPTER)
    fun provideRxJava3CallAdapterFactory(): CallAdapter.Factory =
        RxJava3CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideTalentaResponseInterceptor(
        gson: Gson
    ): MyResponseInterceptor = MyResponseInterceptor(gson)

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()


    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        requestInterceptor: MyRequestInterceptor,
        responseInterceptor: MyResponseInterceptor
    ): OkHttpClient {
        val timeout = DataConstants.TIMEOUT
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .addInterceptor(requestInterceptor)
            .addInterceptor(responseInterceptor)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named(BASE_URL) baseUrl: String,
        @Named(RXJAVA3_CALL_ADAPTER) rxJava3CallAdapterFactory: CallAdapter.Factory,
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(rxJava3CallAdapterFactory)
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .build()

}
