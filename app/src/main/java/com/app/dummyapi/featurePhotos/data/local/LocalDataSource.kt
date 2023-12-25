package com.app.dummyapi.featurePhotos.data.local

import com.app.dummyapi.MyApplication
import com.app.dummyapi.featurePhotos.domain.photos.Photo
import com.app.dummyapi.featurePhotos.domain.photos.PhotosData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

object LocalDataSource {

    fun getPhotosData(): PhotosData? {
        val photoJson = readFromFile()
        return if (!photoJson.isNullOrBlank()) {
            return gson.fromJson(photoJson, object : TypeToken<PhotosData>() {}.type)
        } else {
            null
        }
    }

    fun savePhotosData(photosData: PhotosData) {
        val photosJson = gson.toJson(photosData)
        writeToFile(photosJson)
    }

    private fun writeToFile(data: String) {
        file.printWriter().use { out -> out.println(data) }
    }

    private fun readFromFile(): String? {
        return if (file.exists()) {
            file.inputStream().readBytes().toString(Charsets.UTF_8)
        } else {
            null
        }
    }

    private val file: File by lazy {
        File(MyApplication.context.filesDir, "photosJson.txt")
    }

    private val gson : Gson by lazy { Gson() }
}
