package com.example.myapplication.di.module

import com.example.myapplication.model.api.client.AppApi
import com.example.myapplication.model.repository.WebRepository
import com.example.myapplication.model.repository.impl.WebRepositoryImpl
import com.example.myapplication.model.storage.Storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideWebRepository(
        api: AppApi,
        storage: Storage,
        defaultDispatcher: CoroutineDispatcher
    ): WebRepository {
        return WebRepositoryImpl(api, storage, defaultDispatcher)
    }


    @Provides
    fun provideDefaultCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}
