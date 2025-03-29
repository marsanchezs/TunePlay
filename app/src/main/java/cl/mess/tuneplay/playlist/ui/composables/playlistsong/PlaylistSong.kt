package cl.mess.tuneplay.playlist.ui.composables.playlistsong

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PlaylistSong(attrs: AttrsPlaylistSong) {
    Scaffold(
        topBar = { PlaylistSongTopBar(attrs = attrs.attrsTopBar) }
    ) {
        PlaylistSongContent(attrs = attrs.attrsContent)
    }
}

data class AttrsPlaylistSong(
    val attrsTopBar: AttrsPlaylistSongTopBar,
    val attrsContent: AttrsPlaylistSongContent
)
