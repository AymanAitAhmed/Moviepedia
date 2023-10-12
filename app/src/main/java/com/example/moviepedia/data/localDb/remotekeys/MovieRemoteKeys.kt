package com.example.moviepedia.data.localDb.remotekeys

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviepedia.components.Constants

@Entity(tableName = Constants.REMOTE_KEY_TABLE_NAME)
data class MovieRemoteKeys(
    @PrimaryKey(autoGenerate = true)
    val DbId : Int? = null,
    val id : Int,
    val prevPage : Int?,
    val nextPage : Int?
)
