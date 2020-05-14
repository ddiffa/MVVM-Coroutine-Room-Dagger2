package com.hellodiffa.coroutinesxroom.data.remote

import com.hellodiffa.coroutinesxroom.base.BaseDataSource
import javax.inject.Inject

/*
* created by Diffa
*/

class PlayerRemoteDataSource @Inject constructor(private val service: PlayerService) :
    BaseDataSource() {

    suspend fun getAllPlayers() = getResult {
        service.getAllPlayers()
    }
}