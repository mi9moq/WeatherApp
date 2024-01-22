package com.mironov.weatherapp.di

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import dagger.Module
import dagger.Provides

@Module
class MviModule {

    @AppScope
    @Provides
    fun provideStoreFactory(): StoreFactory = DefaultStoreFactory()
}