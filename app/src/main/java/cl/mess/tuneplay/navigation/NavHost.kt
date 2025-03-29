package cl.mess.tuneplay.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.mess.tuneplay.playlist.ui.PlaylistScreen
import cl.mess.tuneplay.splash.ui.SplashScreen

@Composable
fun NavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = TunePlayRoutes.SplashScreen.path
    ) {
        composable(route = TunePlayRoutes.SplashScreen.path) {
            SplashScreen(navController = navController)
        }
        composable(route = TunePlayRoutes.PlaylistScreen.path) {
            PlaylistScreen()
        }
    }
}
