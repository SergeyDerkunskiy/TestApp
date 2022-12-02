package com.example.myapplication.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.api.model.RemoteData
import com.example.myapplication.model.repository.WebRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WebRepository
) : ViewModel() {

    private val _remoteDataLD = MutableLiveData<RemoteData>()
    val remoteDataLD: LiveData<RemoteData>
        get() = _remoteDataLD

    fun getResponse() {
        viewModelScope.launch {
            _remoteDataLD.postValue( repository.getResponse().body())
        }
    }

    fun saveLaunchCounter(counter: Int) {
        repository.saveLaunchCounter(counter)
    }

    fun getLaunchCounter(): Int? {
        return repository.getLaunchCounter()
    }

    fun saveRemoteData(remoteData: RemoteData) {
        repository.saveRemoteData(remoteData)
    }

    fun getRemoteData(): RemoteData {
        return repository.getRemoteData()
    }


}