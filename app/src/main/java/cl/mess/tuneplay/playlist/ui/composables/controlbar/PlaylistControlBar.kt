package cl.mess.tuneplay.playlist.ui.composables.controlbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.mess.tuneplay.playlist.domain.model.Song
import cl.mess.tuneplay.ui.theme.controlBarColor

@Composable
fun PlaylistControlBar(attrs: AttrsPlaylistControlBar) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 150.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = controlBarColor),
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 8.dp)
        ) {
            PlaylistControlBarTitle(
                onClick = attrs.onClick,
                song = attrs.song
            )

            Spacer(modifier = Modifier.weight(1f))

            PlaylistControlBarSlider(attrs = attrs.attrsSlider)

            Spacer(modifier = Modifier.weight(1f))

            PlaylistControlBarButtons(attrs = attrs.attrsButtons)
        }
    }
}

data class AttrsPlaylistControlBar(
    val song: Song,
    val onClick: () -> Unit,
    val attrsSlider: AttrsSlider,
    val attrsButtons: AttrsButtons
)
