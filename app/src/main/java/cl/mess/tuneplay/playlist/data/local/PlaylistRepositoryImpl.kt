package cl.mess.tuneplay.playlist.data.local

import android.content.ContentResolver
import android.content.ContentUris
import android.provider.MediaStore
import android.util.Log
import androidx.core.net.toUri
import cl.mess.tuneplay.playlist.data.PlaylistRepository
import cl.mess.tuneplay.playlist.data.local.model.LocalSong
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.FileNotFoundException

class PlaylistRepositoryImpl(private val contentResolver: ContentResolver) : PlaylistRepository {

    private var cachedSongs: List<LocalSong>? = null

    override suspend fun getAllSongs(): List<LocalSong> = withContext(Dispatchers.IO) {
        cachedSongs ?: loadSongsFromMediaStore().also { cachedSongs = it }
    }

    private fun loadSongsFromMediaStore(): List<LocalSong> {
        val playlist = mutableListOf<LocalSong>()
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ALBUM_ID
        )
        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"

        contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            sortOrder
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val albumColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
            val albumIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)

            while (cursor.moveToNext()) {
                val song = LocalSong(
                    id = cursor.getLong(idColumn),
                    title = cursor.getString(titleColumn),
                    artist = cursor.getString(artistColumn),
                    album = cursor.getString(albumColumn),
                    duration = cursor.getLong(durationColumn),
                    data = cursor.getString(dataColumn),
                    albumArt = getAlbumArt(cursor.getLong(albumIdColumn))
                )
                playlist.add(song)
            }
        }
        return playlist
    }

    private fun getAlbumArt(albumId: Long): String? {
        val albumArtUri = ContentUris.withAppendedId(
            "content://media/external/audio/albumart".toUri(), albumId
        )
        return try {
            contentResolver.openInputStream(albumArtUri)?.use { albumArtUri.toString() }
        } catch (e: FileNotFoundException) {
            Log.e(
                /* tag = */ "PlaylistRepositoryImpl",
                /* msg = */ "Album art not found for albumId: $albumId",
                /* tr = */ e
            )
            null
        } catch (e: SecurityException) {
            Log.e(
                /* tag = */ "PlaylistRepositoryImpl",
                /* msg = */ "Permission denied accessing album art for albumId: $albumId",
                /* tr = */ e
            )
            null
        }
    }
}
