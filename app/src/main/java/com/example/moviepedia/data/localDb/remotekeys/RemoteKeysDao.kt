package com.example.moviepedia.data.localDb.remotekeys

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface RemoteKeysDao {

    @Upsert
    suspend fun upsertAll( movies : List<MovieRemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE id = :id")
    suspend fun getRemoteKey(id:Int) : MovieRemoteKeys

    @Query("DELETE FROM remote_keys")
    suspend fun clearAll()
}