package cl.mess.tuneplay.playlist.ui.composables

import androidx.compose.runtime.Composable
import cl.mess.tuneplay.playlist.domain.model.Song
import cl.mess.tuneplay.playlist.presentation.PlaylistViewModel
import cl.mess.tuneplay.playlist.ui.composables.controlbar.AttrsButtons
import cl.mess.tuneplay.playlist.ui.composables.controlbar.AttrsPlaylistControlBar
import cl.mess.tuneplay.playlist.ui.composables.controlbar.AttrsSlider
import cl.mess.tuneplay.playlist.ui.composables.controlbar.ButtonActions
import cl.mess.tuneplay.playlist.ui.composables.controlbar.PlaylistControlBar

@Composable
fun PlaylistBottomBar(attrs: AttrsPlaylistBottomBar) {
    val duration = attrs.song.duration.coerceAtLeast(1L)
    val progress = attrs.currentPosition.toFloat() / duration.toFloat()
    val onValueChange: (Float) -> Unit = { newProgress ->
        attrs.viewModel.seekTo((newProgress * duration).toLong())
    }

    PlaylistControlBar(
        attrs = AttrsPlaylistControlBar(
            song = attrs.song,
            onClick = attrs.onShowDialog,
            attrsSlider = AttrsSlider(progress, onValueChange),
            attrsButtons = AttrsButtons(
                isPlaying = attrs.isPlaying,
                isShuffleEnabled = attrs.isShuffleEnabled,
                actions = ButtonActions(
                    skipBackward = { attrs.viewModel.skipBackward() },
                    togglePlayPause = { attrs.viewModel.togglePlayPause() },
                    skipForward = { attrs.viewModel.skipForward() },
                    toggleShuffle = { attrs.viewModel.toggleShuffle() },
                    onClose = { attrs.viewModel.resetPlayerState() }
                )
            )
        )
    )
}

data class AttrsPlaylistBottomBar(
    val song: Song,
    val currentPosition: Long,
    val isPlaying: Boolean,
    val isShuffleEnabled: Boolean,
    val viewModel: PlaylistViewModel,
    val onShowDialog: () -> Unit
)
