package com.mironov.weatherapp.di

import com.mironov.weatherapp.data.datasource.SearchRemoteDataSource
import com.mironov.weatherapp.data.datasource.SearchRemoteDataSourceImpl
import com.mironov.weatherapp.data.repository.SearchRepositoryImpl
import com.mironov.weatherapp.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface SearchModule {

    @AppScope
    @Binds
    fun bindRemoteDataSource(impl: SearchRemoteDataSourceImpl): SearchRemoteDataSource

    @AppScope
    @Binds
    fun bindRepository(impl: SearchRepositoryImpl): SearchRepository
}