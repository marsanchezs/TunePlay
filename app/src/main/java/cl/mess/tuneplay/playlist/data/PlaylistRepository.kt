package cl.mess.tuneplay.playlist.data

import cl.mess.tuneplay.playlist.data.local.model.LocalSong

interface PlaylistRepository {
    suspend fun getAllSongs(): List<LocalSong>
}
