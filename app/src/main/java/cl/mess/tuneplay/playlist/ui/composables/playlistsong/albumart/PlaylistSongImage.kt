package cl.mess.tuneplay.playlist.ui.composables.playlistsong.albumart

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import cl.mess.tuneplay.playlist.domain.model.Song
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun PlaylistSongImage(attrs: AttrsPlaylistSongImage) {
    if (attrs.finalImage != null) {
        AsyncImage(
            model = attrs.finalImage,
            contentDescription = "Rotating Album Art",
            contentScale = ContentScale.Crop,
            modifier = attrs.modifier
        )
    } else {
        PlaylistSongDefaultImage(
            attrs = AttrsPlaylistSongDefaultImage(
                song = attrs.song,
                defaultImage = attrs.defaultImage,
                modifier = attrs.modifier
            )
        )
    }
}

data class AttrsPlaylistSongImage(
    val finalImage: ImageRequest?,
    val song: Song,
    val defaultImage: Painter,
    val modifier: Modifier = Modifier
)
