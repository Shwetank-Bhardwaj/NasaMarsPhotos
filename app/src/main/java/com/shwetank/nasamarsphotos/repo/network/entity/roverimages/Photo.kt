package com.shwetank.nasamarsphotos.repo.network.entity.roverimages

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("camera")
    @Expose
    val cameraDetails: CameraDetails,

    @SerializedName("earth_date")
    @Expose
    val earthDate: String,

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("img_src")
    @Expose
    val imgSrc: String,

    @SerializedName("rover")
    @Expose
    val rover: Rover,

    @SerializedName("sol")
    @Expose
    val sol: Int
)