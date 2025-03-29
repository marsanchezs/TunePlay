package cl.mess.tuneplay.splash.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cl.mess.tuneplay.R
import cl.mess.tuneplay.navigation.TunePlayRoutes
import cl.mess.tuneplay.ui.composables.AttrsTunePlayText
import cl.mess.tuneplay.ui.composables.AttrsTunePlayTextFontFamily
import cl.mess.tuneplay.ui.composables.TunePlayText
import cl.mess.tuneplay.ui.theme.backgroundColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    var isVisible by remember { mutableStateOf(value = true) }

    LaunchedEffect(Unit) {
        delay(timeMillis = 2000)
        isVisible = false
        delay(timeMillis = 300)
        navController.popBackStack()
        navController.navigate(route = TunePlayRoutes.PlaylistScreen.path)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        TunePlayText(
            attrs = AttrsTunePlayText(
                text = stringResource(id = R.string.app_name),
                fontSize = 60.sp,
                color = Color.LightGray,
                attrsFontFamily = AttrsTunePlayTextFontFamily(resId = R.font.oswald_semi_bold),
            )
        )
    }
}
