package com.example.moviepedia.components

import com.example.moviepedia.data.localDb.movie.MovieEntity
import com.example.moviepedia.data.remote.movieLists.Result

fun Result.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id, genre_ids, original_language, original_title, poster_path, release_date, title, vote_average, vote_count
    )
}

fun Int.toGenreType() : String{
        return when (this) {
            28 -> "Action"
            12 -> "Adventure"
            16 -> "Animation"
            35 -> "Comedy"
            80 -> "Crime"
            99 -> "Documentary"
            18 -> "Drama"
            10751 -> "Family"
            14 -> "Fantasy"
            36 -> "History"
            27 -> "Horror"
            10402 -> "Music"
            9648 -> "Mystery"
            10749 -> "Romance"
            878 -> "Science Fiction"
            10770 -> "TV Movie"
            53 -> "Thriller"
            10752 -> "War"
            37 -> "Western"
            else -> ""
        }
    }