package com.example.myapplication.model.repository.impl

import com.example.myapplication.model.api.client.AppApi
import com.example.myapplication.model.api.model.RemoteData
import com.example.myapplication.model.repository.WebRepository
import com.example.myapplication.model.storage.Storage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

class WebRepositoryImpl(
    private val api: AppApi,
    private val prefStorage: Storage,
    private val defaultDispatcher: CoroutineDispatcher
) : WebRepository {

    override suspend fun getResponse(): Response<RemoteData> {
        return withContext(defaultDispatcher) {
            val result = api.getResponse()
            result
        }
    }

    override fun saveLaunchCounter(counter: Int){
        prefStorage.saveLaunchCounter(counter)
    }

    override fun getLaunchCounter(): Int?{
        return prefStorage.getLaunchCounter()
    }

    override fun saveRemoteData(remoteData: RemoteData){
        prefStorage.saveRemoteData(remoteData)
    }

    override fun getRemoteData(): RemoteData{
        return prefStorage.getRemoteData()
    }
}