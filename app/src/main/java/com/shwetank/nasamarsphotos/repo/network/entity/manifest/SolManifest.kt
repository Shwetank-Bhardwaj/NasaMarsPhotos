package com.shwetank.nasamarsphotos.repo.network.entity.manifest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SolManifest(
    @SerializedName("cameras")
    @Expose
    val cameras: List<String>,

    @SerializedName("earth_date")
    @Expose
    val earthDate: String,

    @SerializedName("sol")
    @Expose
    val sol: Int,

    @SerializedName("total_photos")
    @Expose
    val totalPhotos: Int
)