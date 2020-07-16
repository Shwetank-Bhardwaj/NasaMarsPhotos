package com.shwetank.nasamarsphotos.repo.network.entity.roverimages

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.shwetank.nasamarsphotos.repo.network.entity.roverimages.Photo

data class Photos(
    @SerializedName("photos")
    @Expose
    val photos: List<Photo>
)