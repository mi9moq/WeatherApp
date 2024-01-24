package com.mironov.weatherapp.di

import com.mironov.weatherapp.data.datasource.FavouriteLocalDataSource
import com.mironov.weatherapp.data.datasource.FavouriteLocalDataSourceImpl
import com.mironov.weatherapp.data.repository.FavouriteRepositoryImpl
import com.mironov.weatherapp.domain.repository.FavouriteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface FavouriteModule {

    @AppScope
    @Binds
    fun bindLocalDataSource(impl: FavouriteLocalDataSourceImpl): FavouriteLocalDataSource

    @AppScope
    @Binds
    fun bindRepository(impl: FavouriteRepositoryImpl): FavouriteRepository
}