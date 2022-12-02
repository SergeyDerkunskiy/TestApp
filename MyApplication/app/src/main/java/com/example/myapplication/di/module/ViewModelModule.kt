package com.example.myapplication.di.module

import com.example.myapplication.model.repository.WebRepository
import com.example.myapplication.view.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@InstallIn(ViewModelComponent::class)
@Module
class ViewModelModule {

    @Provides
    fun provideMainViewModel(
        repository: WebRepository
    ): MainViewModel {
        return MainViewModel(repository)
    }


}