package com.hellodiffa.coroutinesxroom.data.local.dao

import androidx.room.*
import com.hellodiffa.coroutinesxroom.data.model.Player
import kotlinx.coroutines.flow.Flow

/*
* created by Diffa
*/

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(player: Player)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAll(players: List<Player>)

    @Query("SELECT * FROM players ORDER BY firstName")
    fun loadAllPlayersFlow(): Flow<List<Player>>

    @Update
    suspend fun updatePlayer(player: Player)

    @Query("SELECT * FROM players WHERE id = :id")
    suspend fun loadPlayerByUUID(id: String): Player
}