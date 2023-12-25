package com.app.dummyapi.featurePhotos

import com.app.dummyapi.featurePhotos.domain.photos.Photo

data class Album(
    val albumId: Int,
    val photos : List<Photo>
)