package com.shwetank.nasamarsphotos.repo.network.entity.roverimages

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CameraDetails(
    @SerializedName("full_name")
    @Expose
    var fullName: String,

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("rover_id")
    @Expose
    val roverId: Int
)