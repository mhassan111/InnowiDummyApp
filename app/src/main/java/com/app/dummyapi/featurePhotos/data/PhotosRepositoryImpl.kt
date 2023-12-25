package com.app.dummyapi.featurePhotos.data

import androidx.compose.ui.platform.LocalAutofillTree
import com.app.dummyapi.featurePhotos.data.local.LocalDataSource
import com.app.dummyapi.featurePhotos.data.mappers.toPhotosData
import com.app.dummyapi.featurePhotos.data.remote.DummyApi
import com.app.dummyapi.featurePhotos.domain.photos.PhotosData
import com.app.dummyapi.featurePhotos.domain.repository.PhotosRepository
import com.app.dummyapi.featurePhotos.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PhotosRepositoryImpl(private val dummyApi: DummyApi) : PhotosRepository {

    override suspend fun getPhotosData(loadFromCache: Boolean): Flow<Resource<PhotosData>> {
        return try {
            // load from local
            val localPhotosData = LocalDataSource.getPhotosData()
            if (localPhotosData != null) {
                flow { emit(Resource.Success(localPhotosData)) }
            }

            val result = dummyApi.getPhotos()
            if (result.isSuccessful) {
                val photosData = result.body()?.toPhotosData() ?: PhotosData(emptyList())
                LocalDataSource.savePhotosData(photosData)
                flow { emit(Resource.Success(photosData)) }
            } else {
                val errorMessage = result.errorBody()?.string() ?: "Unknown Error"
                val exception = Throwable(message = "Result Failed")
                flow { emit(Resource.Error(message = errorMessage, exception = exception)) }
            }
        } catch (exception: Exception) {
            flow { emit(Resource.Error(message = "Exception occurred", exception = exception)) }
        }
    }
}