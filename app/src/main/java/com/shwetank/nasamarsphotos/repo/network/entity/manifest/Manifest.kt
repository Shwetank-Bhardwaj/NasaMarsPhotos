package com.shwetank.nasamarsphotos.repo.network.entity.manifest

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Manifest(
    @SerializedName("photo_manifest")
    @Expose
    val photoManifest: PhotoManifest
)