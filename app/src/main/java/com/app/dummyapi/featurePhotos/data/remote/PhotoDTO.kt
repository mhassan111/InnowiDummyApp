package com.app.dummyapi.featurePhotos.data.remote

data class PhotoDTO(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
