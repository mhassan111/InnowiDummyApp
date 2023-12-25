package com.app.dummyapi.featurePhotos

import com.app.dummyapi.featurePhotos.domain.photos.PhotosData
import com.app.dummyapi.featurePhotos.domain.photos.toAlbums
import com.app.dummyapi.featurePhotos.domain.repository.PhotosRepository
import com.app.dummyapi.featurePhotos.domain.util.Resource
import com.app.dummyapi.featurePhotos.repository.FakePhotosRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class PhotosViewModelTest {

    private lateinit var photosViewModel: PhotosViewModel
    private lateinit var photosRepository: PhotosRepository
    private lateinit var fakePhotosRepository: FakePhotosRepository

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        clearAllMocks()
        photosRepository = mockk(relaxed = true)
    }

    @Test
    fun `Loading state should be shown while data is being loaded`() = runTest {
        coEvery { photosRepository.getPhotosData() } coAnswers {
            delay(1_000)
            flow { emit(Resource.Success(PhotosData(emptyList()))) }
        }

        photosViewModel = PhotosViewModel(photosRepository, mainDispatcherRule.testDispatcher)
        runCurrent()

        assertTrue(photosViewModel.state.value.isLoading)
    }

    @Test
    fun `Should show error message if api result failed`() = runTest {
        fakePhotosRepository = FakePhotosRepository()
        fakePhotosRepository.shouldShowError = true

        coEvery { photosRepository.getPhotosData() } coAnswers {
            fakePhotosRepository.getPhotosData()
        }

        photosViewModel = PhotosViewModel(photosRepository, mainDispatcherRule.testDispatcher)
        runCurrent()

        assertEquals("Failed", photosViewModel.state.value.error)
    }

    @Test
    fun `Should show photos if data is loaded`() = runTest {
        fakePhotosRepository = FakePhotosRepository()

        coEvery { photosRepository.getPhotosData() } coAnswers {
            fakePhotosRepository.getPhotosData()
        }

        photosViewModel = PhotosViewModel(photosRepository, mainDispatcherRule.testDispatcher)
        runCurrent()

        assertEquals(fakePhotosRepository.getAlbums(), photosViewModel.state.value.albums)
    }
}

