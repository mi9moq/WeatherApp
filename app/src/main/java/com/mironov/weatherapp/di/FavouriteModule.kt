package com.mironov.weatherapp.di

import com.mironov.weatherapp.data.datasource.FavouriteLocalDataSource
import com.mironov.weatherapp.data.datasource.FavouriteLocalDataSourceImpl
import com.mironov.weatherapp.data.repository.FavouriteRepositoryImpl
import com.mironov.weatherapp.domain.repository.FavouriteRepository
import dagger.Module
import dagger.Provides

@Module
interface FavouriteModule {

    @AppScope
    @Provides
    fun provideLocalDataSource(impl: FavouriteLocalDataSourceImpl): FavouriteLocalDataSource

    @AppScope
    @Provides
    fun provideRepository(impl: FavouriteRepositoryImpl): FavouriteRepository
}