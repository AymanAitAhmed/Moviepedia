package com.example.moviepedia.data.localDb.remotekeys

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll( movies : List<MovieRemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE id = :id")
    suspend fun getRemoteKey(id:Int) : MovieRemoteKeys

    @Query("DELETE FROM remote_keys")
    suspend fun clearAll()
}