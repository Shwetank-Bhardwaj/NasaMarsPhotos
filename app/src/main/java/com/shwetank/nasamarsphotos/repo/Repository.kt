package com.shwetank.nasamarsphotos.repo

import com.shwetank.nasamarsphotos.repo.network.entity.manifest.Manifest
import com.shwetank.nasamarsphotos.repo.network.entity.roverimages.Photos
import com.shwetank.nasamarsphotos.util.DataState
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getMarsRoverImages(earthDate: String, cameraCode: String?): Flow<DataState<Photos>>
    suspend fun getMarsRoverImages(earthDate: String): Flow<DataState<Photos>>
    suspend fun getMarsRoverManifest(): Flow<DataState<Manifest>>

}
