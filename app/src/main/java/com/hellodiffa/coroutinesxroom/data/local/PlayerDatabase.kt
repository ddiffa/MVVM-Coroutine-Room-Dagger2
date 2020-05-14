package com.hellodiffa.coroutinesxroom.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hellodiffa.coroutinesxroom.data.local.dao.PlayerDao
import com.hellodiffa.coroutinesxroom.data.model.Player

/*
* created by Diffa
*/

@Database(entities = [Player::class], version = 1, exportSchema = false)
abstract class PlayerDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao

    companion object {

        @Volatile
        private var instance: PlayerDatabase? = null

        fun getInstance(context: Context): PlayerDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        }

        private fun buildDatabase(context: Context): PlayerDatabase {
            return Room.databaseBuilder(context, PlayerDatabase::class.java, "playerdb")
                .build()
        }
    }
}