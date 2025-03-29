package cl.mess.tuneplay.playlist.domain

import cl.mess.tuneplay.playlist.data.PlaylistRepository
import cl.mess.tuneplay.playlist.domain.mapper.PlaylistMapper
import cl.mess.tuneplay.playlist.domain.model.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository,
    private val mapper: PlaylistMapper
) {
    operator fun invoke(): Flow<List<Song>> = flow {
        /*println("playlist use case ->>>>>>>>> ${repository.getAllSongs()}")
        val playlist = with(receiver = mapper) { repository.getAllSongs().toSongs() }
        emit(value = playlist)*/

        val localSongs = repository.getAllSongs()
        val playlist = with(receiver = mapper) { localSongs.toSongs() }
        emit(value = playlist)
    }
}
