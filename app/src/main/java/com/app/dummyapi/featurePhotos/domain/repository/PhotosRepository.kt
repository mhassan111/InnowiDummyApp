package com.app.dummyapi.featurePhotos.domain.repository

import com.app.dummyapi.featurePhotos.domain.photos.PhotosData
import com.app.dummyapi.featurePhotos.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {
    suspend fun getPhotosData(loadFromCache : Boolean = false) : Flow<Resource<PhotosData>>
}