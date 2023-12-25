package com.app.dummyapi.featurePhotos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.dummyapi.featurePhotos.domain.photos.toAlbums
import com.app.dummyapi.featurePhotos.domain.repository.PhotosRepository
import com.app.dummyapi.featurePhotos.domain.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PhotosViewModel(
    private val photosRepository: PhotosRepository,
    private val dispatcher : CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private var _state = MutableStateFlow(PhotosState())
    val state = _state.asStateFlow()

    init {
        getPhotos()
    }

    fun getPhotos(loadFromCache: Boolean = false) {
        viewModelScope.launch {
            withContext(dispatcher) {
                _state.update { it.copy(isLoading = true) }
                photosRepository.getPhotosData(loadFromCache).collectLatest { result ->
                    when (result) {
                        is Resource.Error -> {
                            _state.update { it.copy(error = result.message ?: "Error Occurred") }
                        }

                        is Resource.Success -> {
                            _state.update { it.copy(albums = result.data?.toAlbums()) }
                        }
                    }
                    _state.update { it.copy(isLoading = false) }
                }
            }
        }
    }
}