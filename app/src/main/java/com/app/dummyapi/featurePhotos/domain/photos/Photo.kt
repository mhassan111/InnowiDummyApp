package com.app.dummyapi.featurePhotos.domain.photos

data class Photo(
    val photoId: Int,
    val albumId: Int,
    val title: String,
    val thumbnailUrl: String
)
