package com.app.dummyapi.featurePhotos.domain.photos

import com.app.dummyapi.featurePhotos.Album

data class PhotosData(val photos: List<Photo>)

fun PhotosData.toAlbums(): List<Album> {
    val albums = photos.groupBy { it.albumId }.toSortedMap()
    return albums.map { Album(it.key, it.value) }
}
