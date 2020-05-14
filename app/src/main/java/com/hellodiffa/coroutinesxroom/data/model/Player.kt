package com.hellodiffa.coroutinesxroom.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/*
* created by Diffa
*/

@Entity(
    tableName = "players",
    indices = [Index(value = ["firstName", "lastName", "rank"], unique = true)]
)
@Parcelize
data class Player(
    val firstName: String = "",
    val lastName: String = "",
    val gender: String = "",
    val rank: Int = 0,
    val country: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val age: Int = 0,
    @PrimaryKey(autoGenerate = false) val id: String = "",
    val points: Long,
    var isFavorite : Boolean = false
) : Parcelable