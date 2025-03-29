package cl.mess.tuneplay.playlist.ui.composables.playlistitem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.tuneplay.R
import cl.mess.tuneplay.playlist.domain.model.Song
import cl.mess.tuneplay.ui.composables.AttrsTunePlayText
import cl.mess.tuneplay.ui.composables.TunePlayText
import cl.mess.tuneplay.utils.formatTime
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun PlaylistItemContent(
    song: Song,
    imageUrl: String
) {
    Row(
        modifier = Modifier.padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = imageUrl)
                .build(),
            contentDescription = song.albumArt,
            modifier = Modifier
                .size(size = 100.dp)
                .clip(RoundedCornerShape(size = 8.dp)),
            contentScale = ContentScale.Crop,
            colorFilter = if (imageUrl.isEmpty()) ColorFilter.tint(color = Color.White) else null,
            error = painterResource(id = R.drawable.ic_headphones_512px),
            placeholder = painterResource(id = R.drawable.ic_headphones_512px)
        )
        Spacer(modifier = Modifier.width(width = 12.dp))
        Column {
            TunePlayText(
                attrs = AttrsTunePlayText(
                    modifier = Modifier.fillMaxWidth(),
                    text = song.title,
                    fontSize = 24.sp,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            )
            TunePlayText(
                attrs = AttrsTunePlayText(
                    modifier = Modifier.fillMaxWidth(),
                    text = formatTime(milliseconds = song.duration),
                    fontSize = 16.sp,
                    color = Color.LightGray
                )
            )
            TunePlayText(
                attrs = AttrsTunePlayText(
                    modifier = Modifier.fillMaxWidth(),
                    text = song.artist,
                    fontSize = 16.sp,
                    color = Color.LightGray
                )
            )
        }
    }
}
