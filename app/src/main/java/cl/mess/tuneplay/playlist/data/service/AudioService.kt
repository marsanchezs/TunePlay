package cl.mess.tuneplay.playlist.data.service

import android.util.Log
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import cl.mess.tuneplay.playlist.data.audio.AudioPlayerManager

class AudioService : MediaSessionService() {

    private lateinit var mediaSession: MediaSession
    private lateinit var player: ExoPlayer
    private lateinit var audioPlayerManager: AudioPlayerManager

    override fun onCreate() {
        super.onCreate()

        Log.d("AudioService", "Servicio iniciado")
        // Inicializar AudioPlayerManager
        audioPlayerManager = AudioPlayerManager(this)

        // Configurar MediaSession con ExoPlayer
        mediaSession = MediaSession.Builder(this, audioPlayerManager.getExoPlayer())
            //.setCallback(sessionCallback)
            .build()

        // Agregar Listener para manejar eventos de reproducción
        mediaSession.player.addListener(playerListener)
    }

    override fun onDestroy() {
        mediaSession.release()
        super.onDestroy()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession {
        return mediaSession
    }

    private val playerListener = object : Player.Listener {
        override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
            if (playWhenReady) {
                audioPlayerManager.resume()
            } else {
                audioPlayerManager.pause()
            }
        }

        override fun onPlaybackStateChanged(state: Int) {
            when (state) {
                Player.STATE_ENDED -> Log.d("AudioService", "Reproducción terminada")
                Player.STATE_BUFFERING -> Log.d("AudioService", "Cargando...")
                Player.STATE_READY -> Log.d("AudioService", "Listo para reproducir")
                else -> Log.d("AudioService", "Estado desconocido: $state")
            }
        }
    }
}
