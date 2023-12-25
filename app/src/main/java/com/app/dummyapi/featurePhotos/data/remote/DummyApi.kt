package com.app.dummyapi.featurePhotos.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface DummyApi {

    // https://jsonplaceholder.typicode.com/photos
    @GET("photos")
    suspend fun getPhotos(): Response<List<PhotoDTO>>

}