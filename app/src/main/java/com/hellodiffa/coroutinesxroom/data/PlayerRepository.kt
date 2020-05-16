package com.hellodiffa.coroutinesxroom.data

import com.hellodiffa.coroutinesxroom.data.local.dao.PlayerDao
import com.hellodiffa.coroutinesxroom.data.model.Player
import com.hellodiffa.coroutinesxroom.data.remote.PlayerRemoteDataSource
import com.hellodiffa.coroutinesxroom.utils.resultLiveData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/*
* created by Diffa
*/

@Singleton
class PlayerRepository @Inject constructor(
    private val dao: PlayerDao,
    private val remote: PlayerRemoteDataSource,
    @Named("IO") private val io: CoroutineDispatcher = IO
) {

    fun observePlayer() = resultLiveData(
        databaseQuery = { dao.loadAllPlayers() },
        networkCall = { remote.getAllPlayers() },
        saveCallResult = { dao.saveAll(it) },
        io = io
    )

    suspend fun updateFavoritePlayer(player: Player) = dao.updatePlayer(player)

    suspend fun observePlayerByUUID(id: String) = dao.loadPlayerByUUID(id)
}