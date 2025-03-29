package cl.mess.tuneplay.playlist.ui.composables.playlistsong.slider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistSongSliderTrack(sliderPositions: SliderState) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 4.dp)
    ) {
        val trackWidth = size.width
        val trackHeight = size.height / 2

        drawLine(
            color = Color.Gray,
            start = Offset(x = 0f, y = trackHeight),
            end = Offset(x = trackWidth, y = trackHeight),
            strokeWidth = 4.dp.toPx()
        )

        drawLine(
            color = Color.Cyan,
            start = Offset(x = 0f, y = trackHeight),
            end = Offset(x = sliderPositions.value * trackWidth, y = trackHeight),
            strokeWidth = 4.dp.toPx()
        )
    }
}
