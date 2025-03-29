package cl.mess.tuneplay.playlist.ui.composables.playlistsong

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PlaylistSongControlImage(attrs: AttrsPlaylistSongControlImage) {
    Image(
        modifier = Modifier
            .size(size = attrs.size)
            .clickable(onClick = attrs.onClick),
        painter = attrs.painter,
        contentDescription = attrs.contentDescription,
        colorFilter = attrs.colorFilter
    )
}

data class AttrsPlaylistSongControlImage(
    val onClick: () -> Unit,
    val painter: Painter,
    val contentDescription: String = "",
    val size: Dp = 50.dp,
    val colorFilter: ColorFilter = ColorFilter.tint(color = Color.White)
)
