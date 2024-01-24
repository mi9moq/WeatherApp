package com.mironov.weatherapp.di

import com.mironov.weatherapp.data.datasource.WeatherRemoteDataSource
import com.mironov.weatherapp.data.datasource.WeatherRemoteDataSourceImpl
import com.mironov.weatherapp.data.repository.WeatherRepositoryImpl
import com.mironov.weatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface WeatherModule {

    @AppScope
    @Binds
    fun bindRemoteDataSource(impl: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource

    @AppScope
    @Binds
    fun bindRepository(impl: WeatherRepositoryImpl): WeatherRepository
}