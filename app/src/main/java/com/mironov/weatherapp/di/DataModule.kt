package com.mironov.weatherapp.di

import com.mironov.weatherapp.data.network.api.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
interface DataModule {

    companion object {

        private const val BASE_URL = "https://api.weatherapi.com/v1/"
        private const val KEY_PARAM = "key"

        @AppScope
        @Provides
        fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val newUrl = originalRequest
                    .url()
                    .newBuilder()
                    .addQueryParameter(KEY_PARAM, com.mironov.weatherapp.BuildConfig.API_KEY)
                    .build()

                val newRequest = originalRequest
                    .newBuilder()
                    .url(newUrl)
                    .build()

                chain.proceed(newRequest)
            }.build()

        @AppScope
        @Provides
        fun provideRetrofit(client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        @AppScope
        @Provides
        fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create()
    }
}