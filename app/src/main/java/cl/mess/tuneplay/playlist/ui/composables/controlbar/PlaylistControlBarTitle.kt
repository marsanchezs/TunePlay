package cl.mess.tuneplay.playlist.ui.composables.controlbar

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import cl.mess.tuneplay.playlist.ui.SharedTransitionConstants.IMAGE_KEY
import cl.mess.tuneplay.playlist.ui.SharedTransitionConstants.TITLE_KEY
import cl.mess.tuneplay.ui.composables.AttrsTunePlayText
import cl.mess.tuneplay.ui.composables.TunePlayText
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PlaylistControlBarTitle(
    onClick: () -> Unit,
    song: Song,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val imageUrl = song.albumArt.takeIf { it.isNotBlank() } ?: ""

    Row(
        modifier = Modifier.clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        with(sharedTransitionScope) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = imageUrl)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = RoundedCornerShape(size = 8.dp))
                    .sharedElement(
                        rememberSharedContentState(key = IMAGE_KEY),
                        animatedVisibilityScope = animatedVisibilityScope
                    ),
                contentScale = ContentScale.Crop,
                colorFilter = if (imageUrl.isEmpty()) ColorFilter.tint(color = Color.White) else null,
                placeholder = painterResource(id = R.drawable.ic_headphones_512px),
                error = painterResource(id = R.drawable.ic_headphones_512px)
            )
            Spacer(modifier = Modifier.width(width = 8.dp))
            Column(
                modifier = Modifier.sharedBounds(
                    rememberSharedContentState(key = TITLE_KEY),
                    animatedVisibilityScope = animatedVisibilityScope
                )
            ) {
                TunePlayText(
                    attrs = AttrsTunePlayText(
                        modifier = Modifier.fillMaxWidth(),
                        text = song.title,
                        fontSize = 20.sp,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
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
}
