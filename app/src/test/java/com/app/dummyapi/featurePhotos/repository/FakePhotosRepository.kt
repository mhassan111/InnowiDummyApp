package com.app.dummyapi.featurePhotos.repository

import com.app.dummyapi.featurePhotos.Album
import com.app.dummyapi.featurePhotos.domain.photos.Photo
import com.app.dummyapi.featurePhotos.domain.photos.PhotosData
import com.app.dummyapi.featurePhotos.domain.photos.toAlbums
import com.app.dummyapi.featurePhotos.domain.repository.PhotosRepository
import com.app.dummyapi.featurePhotos.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePhotosRepository : PhotosRepository {

    var shouldShowError = false
    private val photos = mutableListOf<Photo>()

    init {
        repeat(10) {
            photos.add(Photo(it, it, "Title $it", "Thumbnail $it"))
        }
    }

    override suspend fun getPhotosData(loadFromCache: Boolean): Flow<Resource<PhotosData>> {
        return if (!shouldShowError) {
            flow { emit(Resource.Success(PhotosData(photos))) }
        } else {
            val errorMessage = "Failed"
            val exception = Throwable(message = "Result Failed")
            flow { emit(Resource.Error(message = errorMessage, exception = exception)) }
        }
    }

    fun getAlbums() : List<Album> = PhotosData(photos).toAlbums()
}