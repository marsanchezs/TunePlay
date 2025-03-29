package cl.mess.tuneplay.playlist.data.audio

import android.annotation.SuppressLint
import android.content.Context
import androidx.media3.common.AudioAttributes.Builder
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import cl.mess.tuneplay.playlist.domain.audio.AudioPlayer
import cl.mess.tuneplay.playlist.domain.model.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AudioPlayerManager @Inject constructor(
    private val context: Context
) : AudioPlayer {
    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    override val songs: StateFlow<List<Song>> = _songs.asStateFlow()

    private val _currentSong = MutableStateFlow<Song?>(value = null)
    override val currentSong: StateFlow<Song?> = _currentSong.asStateFlow()

    private val _isPlaying = MutableStateFlow(value = false)
    override val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentPosition = MutableStateFlow(value = 0L)
    override val currentPosition: StateFlow<Long> = _currentPosition.asStateFlow()

    private val _isShuffleEnabled = MutableStateFlow(value = false)
    override val isShuffleEnabled: StateFlow<Boolean> = _isShuffleEnabled.asStateFlow()

    private val shuffleHistory = mutableListOf<Song>()
    private var shuffleIndex = -1

    private val playbackHistory = mutableListOf<Song>()
    private var exoPlayer: ExoPlayer? = null

    init {
        startPositionTracking()
        setupPlayer()
    }

    private fun setupPlayer() {
        exoPlayer = ExoPlayer.Builder(context).build().apply {
            val audioAttributes = Builder()
                .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
                .setUsage(C.USAGE_MEDIA)
                .build()

            setAudioAttributes(audioAttributes, true)
            playWhenReady = true
        }
    }

    fun getExoPlayer(): ExoPlayer {
        return exoPlayer ?: error("ExoPlayer not initialized")
    }

    override fun setSongs(songs: List<Song>) {
        _songs.value = songs
    }

    override fun play(song: Song) {
        _currentSong.value = song
        if (playbackHistory.isEmpty() || playbackHistory.last() != song) {
            playbackHistory.add(element = song)
        }

        exoPlayer?.release()
        exoPlayer = ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(song.data))
            prepare()
            play()
            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(state: Int) {
                    if (state == Player.STATE_ENDED) {
                        skipForward()
                    }
                }
            })
        }

        _isPlaying.value = true
    }

    override fun pause() {
        exoPlayer?.pause()
        _isPlaying.value = false
    }

    override fun resume() {
        exoPlayer?.play()
        _isPlaying.value = true
    }

    override fun stop() {
        exoPlayer?.stop()
        _isPlaying.value = false
    }

    override fun seekTo(position: Long) {
        exoPlayer?.seekTo(position)
    }

    override fun skipForward() {
        val songsList = _songs.value
        val currentIndex = songsList.indexOf(element = _currentSong.value)

        if (songsList.isNotEmpty()) {
            val nextSong = if (_isShuffleEnabled.value) {
                var newSong: Song
                do {
                    newSong = songsList.random()
                } while (newSong == _currentSong.value)
                newSong
            } else {
                if (currentIndex == songsList.size - 1) songsList[0] else songsList[currentIndex + 1]
            }
            play(song = nextSong)
        }

    }

    override fun skipBackward() {
        if (playbackHistory.size > 1) {
            playbackHistory.removeAt(index = playbackHistory.lastIndex)
            playbackHistory.lastOrNull()?.let { play(it) }
        }
    }

    override fun toggleShuffle() {
        _isShuffleEnabled.value = !_isShuffleEnabled.value
    }

    override fun resetPlayerState() {
        stop()
        _isShuffleEnabled.value = false
        _currentSong.value = null
        _isPlaying.value = false
        _isShuffleEnabled.value = false
        _currentPosition.value = 0L
        shuffleHistory.clear()
        shuffleIndex = -1
        playbackHistory.clear()
    }

    override fun release() {
        exoPlayer?.release()
    }

    private fun startPositionTracking() {
        CoroutineScope(Dispatchers.Main.immediate).launch {
            while (true) {
                _currentPosition.value = exoPlayer?.currentPosition ?: 0L
                delay(timeMillis = 1000)
            }
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: AudioPlayerManager? = null

        fun getInstance(context: Context): AudioPlayerManager {
            return instance ?: synchronized(this) {
                instance ?: AudioPlayerManager(context.applicationContext).also { instance = it }
            }
        }
    }
}
