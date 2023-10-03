package com.example.moviepedia.presentation.movie_list_template

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviepedia.components.Screens

@Composable
fun MovieList(
    layoutType: Int,
    navController: NavController
) {

    val list = listOf(
        Movie(0),
        Movie(1),
        Movie(2),
        Movie(3),
        Movie(4),
        Movie(5),
        Movie(6),
        Movie(7),
        Movie(8),
        Movie(9),
        Movie(10),
        Movie(11),
        Movie(12),
        Movie(13),
        Movie(14),
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (layoutType == 0) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(list.size, key = {
                    list[it].id
                }) {
                    val movie = list[it]
                    SmallMovieCard(
                        imageURL = movie.imageURL,
                        title = movie.title,
                        language = movie.language,
                        rating = movie.rating,
                        ratingCount = movie.ratingCount,
                        onClick = {
                            navController.navigate(Screens.MovieDetailsScreen.route)
                        }
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(list.size, key = {
                    list[it].id
                }) {
                    val movie = list[it]
                    LargeMovieCard(
                        imageURL = movie.imageURL,
                        title = movie.title,
                        date = movie.date,
                        language = movie.language,
                        rating = movie.rating,
                        ratingCount = movie.ratingCount,
                        onClick = {
                            navController.navigate(Screens.MovieDetailsScreen.route)
                        }
                    )
                }
            }

        }

    }

}

data class Movie(
    val id: Int,
    val imageURL: String = "https://www.themoviedb.org/t/p/w220_and_h330_face/dhjyfcwEoW6jJ4Q7DpZTp6E58GA.jpg",
    val title: String = "LUCY",
    val date: String = "2023.12.03",
    val language: String = "en",
    val rating: Float = 8.32f,
    val ratingCount: Int = 120364

)
