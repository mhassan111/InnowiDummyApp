package com.app.dummyapi.featurePhotos.data.mappers

import com.app.dummyapi.featurePhotos.data.remote.PhotoDTO
import com.app.dummyapi.featurePhotos.data.remote.PhotosDataDTO
import com.app.dummyapi.featurePhotos.domain.photos.Photo
import com.app.dummyapi.featurePhotos.domain.photos.PhotosData

fun PhotosDataDTO.toPhotosData(): PhotosData {
    return PhotosData(
        photos.map {
            Photo(it.id, it.albumId, it.title, it.thumbnailUrl)
        }
    )
}

fun List<PhotoDTO>.toPhotosData(): PhotosData {
    return PhotosData(
        this.map {
            Photo(it.id, it.albumId, it.title, it.thumbnailUrl)
        }
    )
}