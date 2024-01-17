package com.mironov.weatherapp.di

import com.mironov.weatherapp.data.datasource.SearchRemoteDataSource
import com.mironov.weatherapp.data.datasource.SearchRemoteDataSourceImpl
import com.mironov.weatherapp.data.repository.SearchRepositoryImpl
import com.mironov.weatherapp.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides

@Module
interface SearchModule {

    @AppScope
    @Provides
    fun provideRemoteDataSource(impl: SearchRemoteDataSourceImpl): SearchRemoteDataSource

    @AppScope
    @Provides
    fun provideRepository(impl: SearchRepositoryImpl): SearchRepository
}