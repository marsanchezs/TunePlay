package cl.mess.tuneplay.playlist.ui.composables.playlistsong.slider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.tuneplay.ui.composables.AttrsTunePlayText
import cl.mess.tuneplay.ui.composables.TunePlayText
import cl.mess.tuneplay.utils.formatTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistSongSlider(attrs: AttrsPlaylistSongSlider) {
    val formattedPosition = formatTime(milliseconds = attrs.currentPosition)
    val formattedDuration = formatTime(milliseconds = attrs.duration)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TunePlayText(
            attrs = AttrsTunePlayText(
                text = formattedPosition,
                fontSize = 16.sp,
                color = Color.White
            )
        )
        Spacer(modifier = Modifier.width(width = 8.dp))
        Slider(
            value = attrs.progress,
            onValueChange = attrs.onValueChange,
            modifier = Modifier.weight(weight = 1f),
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.Cyan,
                inactiveTrackColor = Color.Gray
            ),
            track = { sliderPositions ->
                PlaylistSongSliderTrack(sliderPositions = sliderPositions)
            },
            thumb = {
                Canvas(modifier = Modifier.size(size = 14.dp)) {
                    drawCircle(
                        color = Color.White,
                        radius = size.minDimension / 2
                    )
                }
            }
        )
        Spacer(modifier = Modifier.width(width = 8.dp))
        TunePlayText(
            attrs = AttrsTunePlayText(
                text = formattedDuration,
                fontSize = 16.sp,
                color = Color.White
            )
        )
    }
}

data class AttrsPlaylistSongSlider(
    val currentPosition: Long,
    val duration: Long,
    val progress: Float,
    val onValueChange: (Float) -> Unit
)
