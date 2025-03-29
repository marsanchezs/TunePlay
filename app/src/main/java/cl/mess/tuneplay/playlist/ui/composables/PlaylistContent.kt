package cl.mess.tuneplay.playlist.ui.composables

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cl.mess.tuneplay.playlist.domain.model.Song
import cl.mess.tuneplay.ui.composables.TunePlayLoading

@Composable
fun PlaylistContent(attrs: AttrsPlaylistContent) {
    if (attrs.isLoading) {
        TunePlayLoading()
    } else {
        Playlist(
            attrs = AttrsPlaylist(
                songs = attrs.songs,
                currentSong = attrs.currentSong,
                listState = attrs.listState,
                modifier = attrs.modifier
            )
        ) { song -> attrs.onSongClick(song) }
    }
}

data class AttrsPlaylistContent(
    val songs: List<Song>,
    val isLoading: Boolean,
    val currentSong: Song?,
    val listState: LazyListState,
    val onSongClick: (Song) -> Unit,
    val modifier: Modifier
)
