package cl.mess.tuneplay.playlist.ui.composables.playlistsong.albumart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.tuneplay.R
import cl.mess.tuneplay.playlist.domain.model.Song
import cl.mess.tuneplay.ui.composables.AttrsTunePlayText
import cl.mess.tuneplay.ui.composables.AttrsTunePlayTextFontFamily
import cl.mess.tuneplay.ui.composables.TunePlayText
import cl.mess.tuneplay.ui.theme.highlightItemColorDarker
import cl.mess.tuneplay.ui.theme.highlightItemColorLighter

@Composable
fun PlaylistSongDefaultImage(attrs: AttrsPlaylistSongDefaultImage) {
    Box(
        modifier = attrs.modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = attrs.defaultImage,
            contentDescription = "Default Album Art",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(color = attrs.colorImage)
        )
        TunePlayText(
            attrs = AttrsTunePlayText(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-90).dp),
                text = attrs.song.artist.trim(),
                fontSize = 40.sp,
                color = attrs.colorText,
                textAlign = TextAlign.Center,
                attrsFontFamily = AttrsTunePlayTextFontFamily(resId = R.font.oswald_semi_bold),
            )
        )
        TunePlayText(
            attrs = AttrsTunePlayText(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (50).dp),
                text = attrs.song.title.trim(),
                fontSize = 30.sp,
                color = attrs.colorText,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                attrsFontFamily = AttrsTunePlayTextFontFamily(resId = R.font.oswald_medium),
            )
        )
    }
}

data class AttrsPlaylistSongDefaultImage(
    val song: Song,
    val defaultImage: Painter,
    val modifier: Modifier = Modifier,
    val colorImage: Color = highlightItemColorLighter,
    val colorText: Color = highlightItemColorDarker
)
