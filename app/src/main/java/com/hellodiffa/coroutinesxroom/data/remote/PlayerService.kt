package com.hellodiffa.coroutinesxroom.data.remote

import com.hellodiffa.coroutinesxroom.data.model.Player
import retrofit2.Response
import retrofit2.http.GET

/*
* created by Diffa
*/

interface PlayerService {

    companion object {
        const val BASE_URL = "https://xapi-player.herokuapp.com/"
    }

    @GET("api/v1/player/")
    suspend fun getAllPlayers(): Response<List<Player>>
}