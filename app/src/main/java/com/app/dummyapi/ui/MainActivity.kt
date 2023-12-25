package com.app.dummyapi.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.dummyapi.featurePhotos.PhotosViewModel
import com.app.dummyapi.featurePhotos.presentation.AlbumView
import com.app.dummyapi.ui.theme.DummyAppTheme
import com.app.dummyapi.utilities.Injectors

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            DummyAppTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val viewModel = viewModel(initializer = { PhotosViewModel(Injectors.injectPhotosRepository()) })
    val state = viewModel.state.collectAsState().value

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colors.primary)
            } else if (state.error != null) {
                Button(onClick = { viewModel.getPhotos() }) {
                    Text(text = "Load")
                }
            } else {
                AlbumView(albums = state.albums ?: emptyList(), modifier = Modifier.fillMaxSize())
            }
        }
    }
}
