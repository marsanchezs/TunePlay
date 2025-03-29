package cl.mess.tuneplay.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.tuneplay.R
import cl.mess.tuneplay.ui.theme.backgroundColor
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun TunePlayLoading() {
    val compositionClock by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.clock_loading_animation)
    )
    val compositionPoints by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.points_loading_animation)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = compositionClock,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(size = 300.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-50).dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TunePlayText(
                    attrs = AttrsTunePlayText(
                        modifier = Modifier.offset(x = (25).dp),
                        text = stringResource(id = R.string.loading),
                        fontSize = 32.sp,
                        color = Color.LightGray,
                        attrsFontFamily = AttrsTunePlayTextFontFamily(resId = R.font.oswald_semi_bold),
                    )
                )
                LottieAnimation(
                    composition = compositionPoints,
                    iterations = LottieConstants.IterateForever,
                    modifier = Modifier
                        .size(size = 100.dp)
                        .offset(y = (-18).dp)
                )
            }
        }
    }
}
