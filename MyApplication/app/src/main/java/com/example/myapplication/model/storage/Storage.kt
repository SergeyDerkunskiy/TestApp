package com.example.myapplication.model.storage

import com.example.myapplication.model.api.model.RemoteData

interface Storage {
    fun saveLaunchCounter(counter: Int)
    fun getLaunchCounter(): Int?
    fun saveRemoteData(remoteData: RemoteData)
    fun getRemoteData(): RemoteData
}