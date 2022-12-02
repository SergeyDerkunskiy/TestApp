package com.example.myapplication.di.module

import android.content.Context
import com.example.myapplication.model.storage.Storage
import com.example.myapplication.model.storage.impl.StorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class StorageModule {

    @Provides
    fun provideStorage(@ApplicationContext context: Context): Storage {
        return StorageImpl(context)
    }
}