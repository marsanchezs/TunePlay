package cl.mess.tuneplay.playlist.ui.composables.controlbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import cl.mess.tuneplay.R

@Composable
fun PlaylistControlBarButtons(attrs: AttrsButtons) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        PlaylistControlBarImage(
            attrs = AttrsPlaylistControlBarImage(
                onClick = attrs.actions.skipBackward,
                painter = painterResource(id = R.drawable.ic_back_60dp),
                contentDescription = "Back"
            )
        )
        PlaylistControlBarImage(
            attrs = AttrsPlaylistControlBarImage(
                onClick = attrs.actions.togglePlayPause,
                painter = painterResource(
                    id = if (attrs.isPlaying) R.drawable.ic_pause_60dp else R.drawable.ic_play_60dp
                ),
                contentDescription = if (attrs.isPlaying) "Pause" else "Play"
            )
        )
        PlaylistControlBarImage(
            attrs = AttrsPlaylistControlBarImage(
                onClick = attrs.actions.skipForward,
                painter = painterResource(id = R.drawable.ic_next_60dp),
                contentDescription = "Next"
            )
        )
        PlaylistControlBarImage(
            attrs = AttrsPlaylistControlBarImage(
                onClick = attrs.actions.toggleShuffle,
                painter = painterResource(id = R.drawable.ic_shuffle_60dp),
                contentDescription = "Shuffle",
                colorFilter = ColorFilter.tint(
                    color = if (attrs.isShuffleEnabled) Color.White else Color.Gray
                )
            )
        )
        PlaylistControlBarImage(
            attrs = AttrsPlaylistControlBarImage(
                onClick = attrs.actions.onClose,
                painter = painterResource(id = R.drawable.ic_close_24dp),
                contentDescription = "Close"
            )
        )
    }
}

data class AttrsButtons(
    val isPlaying: Boolean,
    val isShuffleEnabled: Boolean,
    val actions: ButtonActions = ButtonActions()
)

data class ButtonActions(
    val skipBackward: () -> Unit = {},
    val togglePlayPause: () -> Unit = {},
    val skipForward: () -> Unit = {},
    val toggleShuffle: () -> Unit = {},
    val onClose: () -> Unit = {}
)
