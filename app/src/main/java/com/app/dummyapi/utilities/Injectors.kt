package com.app.dummyapi.utilities

import com.app.dummyapi.featurePhotos.data.PhotosRepositoryImpl
import com.app.dummyapi.featurePhotos.data.remote.DummyApi
import com.app.dummyapi.featurePhotos.domain.repository.PhotosRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object Injectors {

    fun injectPhotosRepository() : PhotosRepository {
        return PhotosRepositoryImpl(getDummyApi())
    }

    fun getDummyApi() : DummyApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }).build()
            )
            .build()
            .create(DummyApi::class.java)
    }
}