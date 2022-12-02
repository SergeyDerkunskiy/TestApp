package com.example.myapplication.di.module


import com.example.myapplication.model.api.client.AppApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Qualifier
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String {
        return "https://efs5i1ube5.execute-api.eu-central-1.amazonaws.com/prod/"
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create()


    @Provides
    fun provideApi(
        @BaseUrl baseUrl: String,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): AppApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
        return retrofit.create(AppApi::class.java)
    }


    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class BaseUrl
}