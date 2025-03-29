package cl.mess.tuneplay.playlist.data.local.model

data class LocalSong(
    val id: Long?,
    val title: String?,
    val artist: String?,
    val album: String?,
    val duration: Long?,
    val data: String,
    val albumArt: String?
)
