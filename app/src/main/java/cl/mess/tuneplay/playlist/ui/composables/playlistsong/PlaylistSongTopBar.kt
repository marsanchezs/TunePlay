package cl.mess.tuneplay.playlist.ui.composables.playlistsong

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import cl.mess.tuneplay.ui.composables.AttrsTunePlayText
import cl.mess.tuneplay.ui.composables.TunePlayText
import cl.mess.tuneplay.ui.theme.backgroundColor
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistSongTopBar(attrs: AttrsPlaylistSongTopBar) {
    val imageUrl = attrs.imageUrl.takeIf { it.isNotBlank() } ?: ""

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(weight = 1f)) {
                    TunePlayText(
                        attrs = AttrsTunePlayText(
                            text = attrs.title,
                            fontSize = 20.sp,
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    )
                    TunePlayText(
                        attrs = AttrsTunePlayText(
                            text = attrs.artist,
                            fontSize = 16.sp,
                            color = Color.White,
                        )
                    )
                }

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data = imageUrl)
                        .build(),
                    contentDescription = "Cover image",
                    modifier = Modifier
                        .size(size = 50.dp)
                        .clip(RoundedCornerShape(size = 8.dp))
                        .align(alignment = Alignment.CenterVertically),
                    contentScale = ContentScale.Crop,
                    colorFilter = if (imageUrl.isEmpty()) ColorFilter.tint(color = Color.White) else null,
                    placeholder = painterResource(id = R.drawable.ic_headphones_512px),
                    error = painterResource(id = R.drawable.ic_headphones_512px)
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { attrs.onDismiss() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }
    )
}

data class AttrsPlaylistSongTopBar(
    val title: String,
    val artist: String,
    val imageUrl: String,
    val onDismiss: () -> Unit
)
