package com.example.myapplication.model.storage.impl

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.example.myapplication.model.api.model.RemoteData
import com.example.myapplication.model.storage.Storage
import androidx.preference.PreferenceManager
import javax.inject.Inject

class StorageImpl @Inject constructor(private val context: Context) : Storage {

    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    override fun saveLaunchCounter(counter: Int) {
        preferences.edit().putInt(COUNTER, counter).apply()
    }

    override fun getLaunchCounter(): Int = preferences.getInt(COUNTER, 0)

    override fun saveRemoteData(remoteData: RemoteData) {
        val responseJson = Gson().toJson(remoteData)
        preferences.edit().putString(REMOTE_DATA, responseJson).apply()
    }

    override fun getRemoteData(): RemoteData {
        val responseString = preferences.getString(REMOTE_DATA, null)
        return Gson().fromJson(responseString, RemoteData::class.java)
    }

    companion object {
        const val COUNTER = "counter"
        const val REMOTE_DATA = "RemoteData"
    }
}