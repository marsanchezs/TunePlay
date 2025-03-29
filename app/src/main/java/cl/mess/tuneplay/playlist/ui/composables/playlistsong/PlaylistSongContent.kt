package cl.mess.tuneplay.playlist.ui.composables.playlistsong

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.mess.tuneplay.playlist.domain.model.Song
import cl.mess.tuneplay.playlist.ui.composables.playlistsong.albumart.PlaylistSongAlbumArt
import cl.mess.tuneplay.playlist.ui.composables.playlistsong.slider.AttrsPlaylistSongSlider
import cl.mess.tuneplay.playlist.ui.composables.playlistsong.slider.PlaylistSongSlider
import cl.mess.tuneplay.ui.theme.itemColor

@Composable
fun PlaylistSongContent(attrs: AttrsPlaylistSongContent) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = itemColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = 12.dp)
        ) {
            PlaylistSongAlbumArt(
                song = attrs.song,
                isPlaying = attrs.isPlaying
            )
            PlaylistSongSlider(attrs = attrs.attrsSlider)
            PlaylistSongControlButtons(attrs = attrs.attrsControlButtons)
        }
    }
}

data class AttrsPlaylistSongContent(
    val song: Song,
    val isPlaying: Boolean,
    val attrsSlider: AttrsPlaylistSongSlider,
    val attrsControlButtons: AttrsControlButtons
)
