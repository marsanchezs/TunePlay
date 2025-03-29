package cl.mess.tuneplay.playlist.data.service
/*
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaStyleNotificationHelper

class MediaNotificationManager(
    private val context: Context,
    private val mediaSession: MediaSession
) {
    fun getNotification(): Notification {
        val mediaController = mediaSession.controller

        return NotificationCompat.Builder(context, "music_channel")
            .setSmallIcon(R.drawable.ic_music_note)
            .setContentTitle(mediaController.metadata.title)
            .setContentText(mediaController.metadata.artist)
            .setLargeIcon(mediaController.metadata.artworkData?.let { BitmapFactory.decodeByteArray(it, 0, it.size) })
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .addAction(NotificationCompat.Action(R.drawable.ic_previous, "Prev", getPendingIntent(ACTION_PREV)))
            .addAction(NotificationCompat.Action(R.drawable.ic_play, "Play", getPendingIntent(ACTION_PLAY)))
            .addAction(NotificationCompat.Action(R.drawable.ic_next, "Next", getPendingIntent(ACTION_NEXT)))
            .setStyle(MediaStyleNotificationHelper.MediaStyle(mediaSession))
            .setOngoing(true)
            .build()
    }

    private fun getPendingIntent(action: String): PendingIntent {
        val intent = Intent(context, MusicService::class.java).apply { this.action = action }
        return PendingIntent
            .getService(
                context,
                action.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
               )
    }

    companion object {
        const val ACTION_PLAY = "play"
        const val ACTION_PAUSE = "pause"
        const val ACTION_NEXT = "next"
        const val ACTION_PREV = "prev"
    }
}*/
