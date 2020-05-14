package com.hellodiffa.coroutinesxroom.data

import androidx.lifecycle.asLiveData
import com.hellodiffa.coroutinesxroom.data.local.dao.PlayerDao
import com.hellodiffa.coroutinesxroom.data.model.Player
import com.hellodiffa.coroutinesxroom.data.remote.PlayerRemoteDataSource
import com.hellodiffa.coroutinesxroom.utils.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton

/*
* created by Diffa
*/

@Singleton
class PlayerRepository @Inject constructor(
    private val dao: PlayerDao,
    private val remote: PlayerRemoteDataSource
) {

    fun observePlayer() = resultLiveData(
        databaseQuery = { dao.loadAllPlayersFlow().asLiveData() },
        networkCall = { remote.getAllPlayers() },
        saveCallResult = { dao.saveAll(it) }
    )

    suspend fun updateFavoritePlayer(player: Player) = dao.updatePlayer(player)

    suspend fun observePlayerByUUID(id: String) = dao.loadPlayerByUUID(id)
}