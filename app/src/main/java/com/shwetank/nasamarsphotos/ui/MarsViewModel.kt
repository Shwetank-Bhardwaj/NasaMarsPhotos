package com.shwetank.nasamarsphotos.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shwetank.nasamarsphotos.repo.Repository
import com.shwetank.nasamarsphotos.repo.network.entity.manifest.Manifest
import com.shwetank.nasamarsphotos.repo.network.entity.roverimages.Photos
import com.shwetank.nasamarsphotos.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MarsViewModel
@ViewModelInject
constructor(
    private val repository: Repository
) : ViewModel() {

    private val _marsRoverPhotos: MutableLiveData<DataState<Photos>> = MutableLiveData()
    val marsRoverPhotos: LiveData<DataState<Photos>> get() = _marsRoverPhotos

    private val _marsRoverManifest: MutableLiveData<DataState<Manifest>> = MutableLiveData()
    val marsRoverManifest: LiveData<DataState<Manifest>> get() = _marsRoverManifest

    init {
        getRoverManifest()
    }

    fun getRoverImages(earthDate: String, cameraCode: String?) {
        viewModelScope.launch {
            repository.getMarsRoverImages(earthDate, cameraCode).onEach {
                _marsRoverPhotos.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun getRoverImages(earthDate: String) {
        viewModelScope.launch {
            repository.getMarsRoverImages(earthDate).onEach {
                _marsRoverPhotos.value = it
            }.launchIn(viewModelScope)
        }
    }

    private fun getRoverManifest() {
        viewModelScope.launch {
            repository.getMarsRoverManifest().onEach {
                _marsRoverManifest.value = it
            }.launchIn(viewModelScope)
        }
    }



}