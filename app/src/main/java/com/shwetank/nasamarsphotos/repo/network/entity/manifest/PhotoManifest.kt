package com.shwetank.nasamarsphotos.repo.network.entity.manifest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhotoManifest(

    @SerializedName("landing_date")
    @Expose
    val landingDate: String,

    @SerializedName("launch_date")
    @Expose
    val launchDate: String,

    @SerializedName("max_date")
    @Expose
    val maxDate: String,

    @SerializedName("max_sol")
    @Expose
    val maxSol: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("photos")
    @Expose
    val solManifests: List<SolManifest>,

    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("total_photos")
    @Expose
    val totalPhotos: Int
)