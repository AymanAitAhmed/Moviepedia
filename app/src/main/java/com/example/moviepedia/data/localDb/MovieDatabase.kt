package com.example.moviepedia.data.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviepedia.data.localDb.movie.MovieDao
import com.example.moviepedia.data.localDb.movie.MovieEntity
import com.example.moviepedia.data.localDb.remotekeys.MovieRemoteKeys
import com.example.moviepedia.data.localDb.remotekeys.RemoteKeysDao

@Database(
    entities = [MovieEntity::class,MovieRemoteKeys::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao : MovieDao
    abstract val remoteKeys : RemoteKeysDao
}