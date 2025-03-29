package cl.mess.tuneplay.playlist.domain.audio

import cl.mess.tuneplay.playlist.domain.model.Song
import kotlinx.coroutines.flow.StateFlow

interface AudioPlayer {
    val songs: StateFlow<List<Song>>
    val currentSong: StateFlow<Song?>
    val isPlaying: StateFlow<Boolean>
    val currentPosition: StateFlow<Long>
    val isShuffleEnabled: StateFlow<Boolean>

    fun setSongs(songs: List<Song>)
    fun play(song: Song)
    fun pause()
    fun resume()
    fun stop()
    fun seekTo(position: Long)
    fun skipForward()
    fun skipBackward()
    fun toggleShuffle()
    fun resetPlayerState()
    fun release()
}
