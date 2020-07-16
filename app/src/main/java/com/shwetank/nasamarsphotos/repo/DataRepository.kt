package com.shwetank.nasamarsphotos.repo

import com.shwetank.nasamarsphotos.repo.network.MarsRoverPhotoAPI
import com.shwetank.nasamarsphotos.repo.network.entity.manifest.Manifest
import com.shwetank.nasamarsphotos.repo.network.entity.roverimages.Photos
import com.shwetank.nasamarsphotos.util.Constants
import com.shwetank.nasamarsphotos.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class DataRepository
    constructor(
        private val marsRoverImageApi: MarsRoverPhotoAPI
    ) : Repository {

    override suspend fun getMarsRoverImages(earthDate: String): Flow<DataState<Photos>> = flow {
        emit(DataState.Loading)
        try {
            val images = marsRoverImageApi.getMarsImages(earthDate, Constants.API_KEY)
            emit((DataState.Success(images)))
        } catch (e: Exception){
            emit(DataState.Error(e))
        }
    }

    override suspend fun getMarsRoverManifest(): Flow<DataState<Manifest>> = flow {
        emit(DataState.Loading)
        try {
            val manifest = marsRoverImageApi.getCuriosityManifest(Constants.API_KEY)
            emit((DataState.Success(manifest)))
        } catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}