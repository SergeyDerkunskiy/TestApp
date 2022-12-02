package com.example.myapplication.model.api.client

import com.example.myapplication.model.api.model.RemoteData
import retrofit2.Response
import retrofit2.http.GET

interface AppApi {
    @GET(".")
    suspend fun getResponse(): Response<RemoteData>
}