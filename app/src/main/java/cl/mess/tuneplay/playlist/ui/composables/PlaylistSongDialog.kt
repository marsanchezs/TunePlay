package cl.mess.tuneplay.playlist.ui.composables

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import cl.mess.tuneplay.playlist.domain.model.Song
import cl.mess.tuneplay.playlist.presentation.PlaylistViewModel
import cl.mess.tuneplay.playlist.ui.composables.controlbar.ButtonActions
import cl.mess.tuneplay.playlist.ui.composables.playlistsong.AttrsControlButtons
import cl.mess.tuneplay.playlist.ui.composables.playlistsong.AttrsPlaylistSong
import cl.mess.tuneplay.playlist.ui.composables.playlistsong.AttrsPlaylistSongContent
import cl.mess.tuneplay.playlist.ui.composables.playlistsong.AttrsPlaylistSongTopBar
import cl.mess.tuneplay.playlist.ui.composables.playlistsong.PlaylistSong
import cl.mess.tuneplay.playlist.ui.composables.playlistsong.slider.AttrsPlaylistSongSlider

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PlaylistSongDialog(attrs: AttrsPlaylistSongDialog) {
    val progress = attrs.currentPosition.toFloat() / attrs.song.duration.toFloat()
    val onValueChange: (Float) -> Unit = { newProgress ->
        attrs.viewModel.seekTo((newProgress * attrs.song.duration).toLong())
    }

    PlaylistSong(
        attrs = AttrsPlaylistSong(
            attrsTopBar = AttrsPlaylistSongTopBar(
                title = attrs.song.title,
                artist = attrs.song.artist,
                imageUrl = attrs.song.albumArt,
                onDismiss = attrs.onDismiss,
                animatedVisibilityScope = attrs.animatedVisibilityScope,
                sharedTransitionScope = attrs.sharedTransitionScope
            ),
            attrsContent = AttrsPlaylistSongContent(
                song = attrs.song,
                isPlaying = attrs.isPlaying,
                attrsSlider = AttrsPlaylistSongSlider(
                    currentPosition = attrs.currentPosition,
                    duration = attrs.song.duration,
                    progress = progress,
                    onValueChange = onValueChange
                ),
                attrsControlButtons = AttrsControlButtons(
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
    )
}

data class AttrsPlaylistSongDialog @OptIn(ExperimentalSharedTransitionApi::class) constructor(
    val song: Song,
    val isPlaying: Boolean,
    val isShuffleEnabled: Boolean,
    val currentPosition: Long,
    val onDismiss: () -> Unit,
    val viewModel: PlaylistViewModel,
    val sharedTransitionScope: SharedTransitionScope,
    val animatedVisibilityScope: AnimatedVisibilityScope
)
