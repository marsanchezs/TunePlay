package cl.mess.tuneplay.playlist.ui.composables.playlistsong.albumart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cl.mess.tuneplay.R
import cl.mess.tuneplay.playlist.domain.model.Song
import cl.mess.tuneplay.ui.theme.controlBarColor
import cl.mess.tuneplay.ui.theme.highlightItemColor
import cl.mess.tuneplay.ui.theme.itemColor
import coil.request.ImageRequest
import kotlinx.coroutines.delay

@Composable
fun PlaylistSongAlbumArt(
    song: Song,
    isPlaying: Boolean
) {
    val imageUrl = song.albumArt.takeIf { it.isNotBlank() } ?: ""
    val defaultImage = painterResource(id = R.drawable.ic_headphones_512px)
    val context = LocalContext.current

    val finalImage = if (imageUrl.isNotBlank()) {
        ImageRequest.Builder(context)
            .data(data = imageUrl)
            .build()
    } else {
        null
    }

    var rotation by remember { mutableFloatStateOf(value = 0f) }

    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            while (true) {
                delay(timeMillis = 16L)
                rotation += ROTATION_INCREMENT
            }
        }
    }

    Box(
        modifier = Modifier
            .size(size = 300.dp)
            .clip(shape = CircleShape)
            .rotate(degrees = rotation)
            .background(color = if (imageUrl.isNotBlank()) Color.Transparent else controlBarColor)
    ) {
        PlaylistSongImage(
            attrs = AttrsPlaylistSongImage(
                finalImage = finalImage,
                song = song,
                defaultImage = defaultImage,
                modifier = Modifier
                    .matchParentSize()
                    .background(color = if (finalImage != null) Color.Transparent else highlightItemColor)
            )
        )

        Canvas(
            modifier = Modifier
                .matchParentSize()
        ) {
            drawCircle(
                color = itemColor,
                radius = size.minDimension * 0.10f,
                center = center
            )
        }
    }
}

private const val ROTATION_INCREMENT = 0.36f
