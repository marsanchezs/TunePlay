package cl.mess.tuneplay.playlist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cl.mess.tuneplay.playlist.domain.model.Song
import cl.mess.tuneplay.playlist.presentation.PlaylistViewModel
import cl.mess.tuneplay.playlist.ui.composables.AttrsPlaylistBottomBar
import cl.mess.tuneplay.playlist.ui.composables.AttrsPlaylistContent
import cl.mess.tuneplay.playlist.ui.composables.AttrsPlaylistSongDialog
import cl.mess.tuneplay.playlist.ui.composables.PlaylistBottomBar
import cl.mess.tuneplay.playlist.ui.composables.PlaylistContent
import cl.mess.tuneplay.playlist.ui.composables.PlaylistSongDialog
import cl.mess.tuneplay.ui.theme.backgroundColor

@Composable
fun PlaylistScreen(viewModel: PlaylistViewModel = hiltViewModel()) {
    val songs by viewModel.songs.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val currentSong by viewModel.currentSong.collectAsState()
    val isPlaying by viewModel.isPlaying.collectAsState()
    val isShuffleEnabled by viewModel.isShuffleEnabled.collectAsState()
    val currentPosition by viewModel.currentPosition.collectAsState()
    val listState = rememberLazyListState()
    var isDialogVisible by remember { mutableStateOf(value = false) }
    var selectedSong by remember { mutableStateOf<Song?>(value = null) }

    LaunchedEffect(currentSong) {
        val currentIndex = songs.indexOfFirst { it.id == currentSong?.id }
        if (currentIndex != -1) listState.scrollToItem(currentIndex)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
    ) {
        PlaylistContent(
            attrs = AttrsPlaylistContent(
                songs = songs,
                isLoading = isLoading,
                currentSong = currentSong,
                listState = listState,
                onSongClick = viewModel::playSong,
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .weight(weight = 1f)
            )
        )
        Spacer(modifier = Modifier.height(height = 8.dp))
        currentSong?.let { song ->
            PlaylistBottomBar(
                attrs = AttrsPlaylistBottomBar(
                    song = song,
                    currentPosition = currentPosition,
                    isPlaying = isPlaying,
                    isShuffleEnabled = isShuffleEnabled,
                    viewModel = viewModel,
                    onShowDialog = {
                        selectedSong = song
                        isDialogVisible = true
                    }
                )
            )
        }
    }

    if (isDialogVisible && currentSong != null) {
        currentSong?.let { song ->
            PlaylistSongDialog(
                attrs = AttrsPlaylistSongDialog(song = song, isPlaying = isPlaying,
                    isShuffleEnabled = isShuffleEnabled, currentPosition = currentPosition,
                    onDismiss = { isDialogVisible = false }, viewModel = viewModel)
            )
        }
    }
}
