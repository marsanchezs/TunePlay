package cl.mess.tuneplay.navigation

import cl.mess.tuneplay.navigation.Constants.PLAYLIST_SCREEN
import cl.mess.tuneplay.navigation.Constants.SPLASH_SCREEN

sealed class TunePlayRoutes(val path: String) {
    data object PlaylistScreen : TunePlayRoutes(path = PLAYLIST_SCREEN)
    data object SplashScreen : TunePlayRoutes(path = SPLASH_SCREEN)
}
