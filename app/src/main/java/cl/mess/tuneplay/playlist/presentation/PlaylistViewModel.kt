package cl.mess.tuneplay.playlist.presentation

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.mess.tuneplay.playlist.data.service.AudioService
import cl.mess.tuneplay.playlist.domain.GetPlaylistUseCase
import cl.mess.tuneplay.playlist.domain.audio.AudioPlayer
import cl.mess.tuneplay.playlist.domain.model.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(
    private val getPlaylistUseCase: GetPlaylistUseCase,
    private val audioPlayer: AudioPlayer
) : ViewModel() {
    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _songs.asStateFlow()
    val currentSong = audioPlayer.currentSong
    val isPlaying = audioPlayer.isPlaying
    val currentPosition = audioPlayer.currentPosition
    val isShuffleEnabled = audioPlayer.isShuffleEnabled

    private val _isLoading = MutableStateFlow(value = true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        fetchSongs()
    }

    private fun fetchSongs() {
        viewModelScope.launch {
            getPlaylistUseCase().collect { playlist ->
                val songList = playlist.toList()
                _songs.value = songList
                audioPlayer.setSongs(songs = songList)
                _isLoading.value = false
            }
        }
    }

    fun playSong(song: Song) = audioPlayer.play(song)

    fun togglePlayPause() {
        if (isPlaying.value) audioPlayer.pause() else audioPlayer.resume()
    }

    fun skipForward() = audioPlayer.skipForward()

    fun skipBackward() = audioPlayer.skipBackward()

    fun toggleShuffle() = audioPlayer.toggleShuffle()

    fun seekTo(position: Long) = audioPlayer.seekTo(position)

    fun resetPlayerState() = audioPlayer.resetPlayerState()

    fun startMusicService(context: Context) {
        val intent = Intent(context, AudioService::class.java)
        ContextCompat.startForegroundService(context, intent)
    }

    fun stopMusicService(context: Context) {
        val intent = Intent(context, AudioService::class.java)
        context.stopService(intent)
    }

    override fun onCleared() {
        super.onCleared()
        audioPlayer.release()
    }
}
