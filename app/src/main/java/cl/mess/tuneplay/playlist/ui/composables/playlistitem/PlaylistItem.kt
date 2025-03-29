package cl.mess.tuneplay.playlist.ui.composables.playlistitem

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import cl.mess.tuneplay.playlist.domain.model.Song
import cl.mess.tuneplay.ui.theme.highlightItemColor
import cl.mess.tuneplay.ui.theme.itemColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PlaylistItem(
    song: Song,
    isPlaying: Boolean,
    onClick: () -> Unit
) {
    val imageUrl = song.albumArt.takeIf { it.isNotBlank() } ?: ""
    val backgroundColor by animateColorAsState(
        if (isPlaying) highlightItemColor else itemColor
    )

    var isPressed by remember { mutableStateOf(value = false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.5f else 1f,
        animationSpec = tween(durationMillis = 150),
        label = "Scale Animation"
    )

    val coroutineScope = rememberCoroutineScope()

    val onItemClicked: () -> Unit = {
        coroutineScope.launch {
            isPressed = true
            delay(timeMillis = 100)
            onClick()
            isPressed = false
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .clickable(onClick = onItemClicked),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        PlaylistItemContent(
            song = song,
            imageUrl = imageUrl
        )
    }
}
