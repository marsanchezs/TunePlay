package cl.mess.tuneplay.playlist.domain.mapper

import cl.mess.tuneplay.playlist.data.local.model.LocalSong
import cl.mess.tuneplay.playlist.domain.model.Song
import javax.inject.Inject

class PlaylistMapper @Inject constructor() {

    fun List<LocalSong>.toSongs() = map { localSong -> localSong.toSong() }

    private fun LocalSong.toSong() = Song(
        id = id ?: 0L,
        title = title ?: "",
        artist = artist ?: "",
        album = album ?: "",
        duration = duration ?: 0L,
        data = data,
        albumArt = albumArt ?: ""
    )
}
