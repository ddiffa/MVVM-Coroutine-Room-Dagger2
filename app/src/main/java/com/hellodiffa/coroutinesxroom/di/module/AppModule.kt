package com.hellodiffa.coroutinesxroom.di.module

import android.app.Application
import android.content.Context
import com.hellodiffa.coroutinesxroom.TennisPlayerApp
import com.hellodiffa.coroutinesxroom.data.PlayerRepository
import com.hellodiffa.coroutinesxroom.data.local.PlayerDatabase
import com.hellodiffa.coroutinesxroom.data.local.dao.PlayerDao
import com.hellodiffa.coroutinesxroom.data.remote.PlayerRemoteDataSource
import com.hellodiffa.coroutinesxroom.data.remote.PlayerService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/*
* created by Diffa
*/

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: TennisPlayerApp): Context = app

    @Provides
    @Singleton
    fun provideApplication(app: TennisPlayerApp): Application = app

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): PlayerService =
        retrofit.create(PlayerService::class.java)

    @Provides
    @Singleton
    fun provideRoomDB(app: Application) = PlayerDatabase.getInstance(app)

    @Provides
    @Singleton
    fun providePlayerDao(db: PlayerDatabase) = db.playerDao()

    @Provides
    @Singleton
    @Named("IO")
    fun provideBackgroundDispatchers(): CoroutineDispatcher =
        Dispatchers.IO

    @Provides
    @Singleton
    @Named("MAIN")
    fun provideMainDispatchers(): CoroutineDispatcher =
        Dispatchers.Main

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: PlayerService) = PlayerRemoteDataSource(apiService)

    @Provides
    @Singleton
    fun providePlayerRepository(dao: PlayerDao, remote: PlayerRemoteDataSource) =
        PlayerRepository(dao, remote)

}