package cl.mess.tuneplay.playlist.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.mess.tuneplay.playlist.domain.model.Song
import cl.mess.tuneplay.playlist.ui.composables.playlistitem.PlaylistItem

@Composable
fun Playlist(
    attrs: AttrsPlaylist,
    onSongClick: (Song) -> Unit
) {
    LazyColumn(
        state = attrs.listState,
        verticalArrangement = Arrangement.spacedBy(space = 20.dp),
        modifier = attrs.modifier
    ) {
        items(
            items = attrs.songs,
            key = { it.id }
        ) { song ->
            PlaylistItem(
                song = song,
                isPlaying = song.id == attrs.currentSong?.id,
                onClick = { onSongClick(song) }
            )
        }
    }
}

data class AttrsPlaylist(
    val songs: List<Song>,
    val currentSong: Song?,
    val listState: LazyListState,
    val modifier: Modifier
)
