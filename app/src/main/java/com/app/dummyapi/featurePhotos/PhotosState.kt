package com.app.dummyapi.featurePhotos

data class PhotosState(
    val albums: List<Album>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)