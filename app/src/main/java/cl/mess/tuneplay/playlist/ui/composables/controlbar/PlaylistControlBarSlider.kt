package cl.mess.tuneplay.playlist.ui.composables.controlbar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistControlBarSlider(attrs: AttrsSlider) {
    Slider(
        value = attrs.progress,
        onValueChange = attrs.onValueChange,
        modifier = Modifier.fillMaxWidth(),
        colors = SliderDefaults.colors(
            thumbColor = Color.White,
            activeTrackColor = Color.Cyan,
            inactiveTrackColor = Color.Gray
        ),
        track = { sliderPositions ->
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp) // 🔹 Grosor más delgado de la línea
            ) {
                val trackWidth = size.width
                val trackHeight = size.height / 2

                drawLine(
                    color = Color.Gray,
                    start = Offset(x = 0f, y = trackHeight),
                    end = Offset(x = trackWidth, y = trackHeight),
                    strokeWidth = 4.dp.toPx() // 🔹 Grosor ajustado
                )

                drawLine(
                    color = Color.Cyan,
                    start = Offset(x = 0f, y = trackHeight),
                    end = Offset(x = sliderPositions.value * trackWidth, y = trackHeight),
                    strokeWidth = 4.dp.toPx()
                )
            }
        },
        thumb = {}
    )
}


data class AttrsSlider(
    val progress: Float = 0f,
    val onValueChange: (Float) -> Unit = {}
)
