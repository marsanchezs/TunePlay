package cl.mess.tuneplay.di

import android.content.ContentResolver
import android.content.Context
import cl.mess.tuneplay.playlist.data.PlaylistRepository
import cl.mess.tuneplay.playlist.data.audio.AudioPlayerManager
import cl.mess.tuneplay.playlist.data.local.PlaylistRepositoryImpl
import cl.mess.tuneplay.playlist.domain.GetPlaylistUseCase
import cl.mess.tuneplay.playlist.domain.audio.AudioPlayer
import cl.mess.tuneplay.playlist.domain.mapper.PlaylistMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContentResolver(@ApplicationContext context: Context): ContentResolver {
        return context.contentResolver
    }

    @Provides
    @Singleton
    fun providePlaylistRepository(contentResolver: ContentResolver): PlaylistRepository {
        return PlaylistRepositoryImpl(contentResolver = contentResolver)
    }

    @Provides
    fun provideGetPlaylistUseCase(
        repository: PlaylistRepository,
        mapper: PlaylistMapper
    ): GetPlaylistUseCase {
        return GetPlaylistUseCase(repository = repository, mapper = mapper)
    }

    @Provides
    @Singleton
    fun provideAudioPlayerManager(@ApplicationContext context: Context): AudioPlayer {
        return AudioPlayerManager(context = context)
    }
}
