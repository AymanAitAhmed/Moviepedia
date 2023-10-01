package com.example.moviepedia.presentation.movieDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviepedia.data.movieDetails.Genre
import com.example.moviepedia.data.movieDetails.MovieDetails
import com.example.moviepedia.data.movieDetails.ProductionCompany
import com.example.moviepedia.data.movieDetails.SpokenLanguage
import com.example.moviepedia.ui.theme.MoviepediaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    onNavigateBackClick : () -> Unit,
    onSearchClick : () -> Unit
) {

    val scrollableState = rememberScrollState()

    val movieDetails = MovieDetails(
        backdrop_path = "",
        genres = listOf(Genre(18), Genre(80)),
        homepage = "",
        original_title = "The Godfather",
        overview = "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
        popularity = 110.79,
        poster_path = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
        production_companies = listOf(
            ProductionCompany(
                id = 4,
                logo_path = "/gz66EfNoYPqHTYI4q9UEN4CbHRc.png",
                name = "Paramount",
                origin_country = "US"
            ),
            ProductionCompany(
                id = 5,
                logo_path = "/gz66EfNoYPqHTYI4q9UEN4CbHRc.png",
                name = "Paramount",
                origin_country = "US"
            ),
            ProductionCompany(
                id = 6,
                logo_path = "/gz66EfNoYPqHTYI4q9UEN4CbHRc.png",
                name = "Paramount",
                origin_country = "US"
            ),
        ),
        release_date = "1972-03-14",
        runtime = 175,
        spoken_languages = listOf(
            SpokenLanguage("English"),
            SpokenLanguage("Italian"),
            SpokenLanguage("Latin"),
        ),
        status = "released",
        title = "The GodFather",
        vote_average = 8.707,
        vote_count = 18684
    )
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp),
        topBar = {
            MovieDetailsTopBar(
                onNavigateBackClick = onNavigateBackClick,
                onSearchClick = onSearchClick
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .scrollable(scrollableState, orientation = Orientation.Vertical)
        ) {
            MovieUpperCard(
                imageURL = movieDetails.poster_path,
                title = movieDetails.title,
                date = movieDetails.release_date,
                duration = movieDetails.runtime,
                releaseState = movieDetails.status,
                rating = movieDetails.vote_average.toFloat(),
                ratingCount = movieDetails.vote_count,
                popularity = movieDetails.popularity.toFloat()
            )

            OverviewSection(movieDetails.overview)

            GenresLazyRow(genresList = movieDetails.genres)
            Spacer(modifier = Modifier.height(8.dp))
            LanguagesSpokenLazyRow(languagesList = movieDetails.spoken_languages)
            Spacer(modifier = Modifier.height(8.dp))
            ProductionCompaniesSection(productionCompaniesList = movieDetails.production_companies)
        }
    }

}