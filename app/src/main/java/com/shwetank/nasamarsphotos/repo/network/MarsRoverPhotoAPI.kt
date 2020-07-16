package com.shwetank.nasamarsphotos.repo.network

import com.shwetank.nasamarsphotos.repo.network.entity.manifest.Manifest
import com.shwetank.nasamarsphotos.repo.network.entity.roverimages.Photos
import retrofit2.http.GET
import retrofit2.http.Query


interface MarsRoverPhotoAPI {

    @GET ("rovers/curiosity/photos")
    suspend fun getMarsImages(
        @Query("earth_date")date: String,
        @Query("api_key")apiKey: String
    ): Photos

    @GET("manifests/curiosity")
    suspend fun getCuriosityManifest(
        @Query("api_key")apiKey: String
    ): Manifest

}