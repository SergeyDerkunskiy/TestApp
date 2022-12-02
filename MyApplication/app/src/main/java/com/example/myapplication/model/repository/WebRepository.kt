package com.example.myapplication.model.repository

import com.example.myapplication.model.api.model.RemoteData
import retrofit2.Response

interface WebRepository {
    suspend fun getResponse(): Response<RemoteData>
    fun saveLaunchCounter(counter: Int)
    fun getLaunchCounter(): Int?
    fun saveRemoteData(remoteData: RemoteData)
    fun getRemoteData(): RemoteData
}