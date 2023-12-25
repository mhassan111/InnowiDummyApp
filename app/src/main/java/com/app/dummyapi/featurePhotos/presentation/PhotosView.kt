package com.app.dummyapi.featurePhotos.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.dummyapi.featurePhotos.Album
import com.app.dummyapi.featurePhotos.domain.photos.Photo
import com.app.dummyapi.ui.styles.normalTextHintStyle


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlbumView(albums: List<Album>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        albums.forEach { album ->
            stickyHeader {
                AlbumHeader("Album ${album.albumId}")
            }
            items(album.photos.size) { index ->
                if (index == 0) {
                    RowTitle()
                }
                AlbumRow(photo = album.photos[index])
            }
        }
    }
}

@Composable
private fun AlbumHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(16.dp)
    )
}

@Composable
private fun AlbumRow(
    photo: Photo,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        TableCell(modifier = Modifier.fillMaxHeight(1f), text = photo.title)
        TableCell(modifier = Modifier.fillMaxHeight(1f), text = photo.thumbnailUrl)
    }
}

@Composable
private fun RowTitle(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        TableCell(modifier = Modifier.fillMaxHeight(1f), text = "Title")
        TableCell(modifier = Modifier.fillMaxHeight(1f), text = "Thumbnail Url")
    }
}

@Composable
fun RowScope.TableCell(
    modifier: Modifier,
    text: String,
    weight: Float = 0.5f
) {
    Text(
        text = text,
        modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(5.dp),
        style = normalTextHintStyle
    )
}
