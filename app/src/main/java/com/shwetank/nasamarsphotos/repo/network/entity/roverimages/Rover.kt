package com.shwetank.nasamarsphotos.repo.network.entity.roverimages

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rover(

    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("landing_date")
    @Expose
    val landingDate: String,

    @SerializedName("launch_date")
    @Expose
    val launchDate: String,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("status")
    @Expose
    val status: String
)