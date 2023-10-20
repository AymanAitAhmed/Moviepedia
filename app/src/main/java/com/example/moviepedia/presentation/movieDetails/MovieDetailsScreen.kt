package com.example.moviepedia.presentation.movieDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.moviepedia.components.Constants
import com.example.moviepedia.data.remote.movieDetails.MovieDetailsResponse

@Composable
fun MovieDetailsScreen(
    movieDetails: MovieDetailsResponse?,
    onNavigateBackClick: () -> Unit,
    onSearchClick: () -> Unit
) {

    val scrollableState = rememberScrollState()

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
        movieDetails?.let { movieDetails ->
            movieDetails.error?.let {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = it)
                }
            } ?: run {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    println(movieDetails.backdrop_path)
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("${Constants.IMAGE_BASE_URL}${movieDetails.backdrop_path}")
                            .crossfade(1000)
                            .build(),
                        contentDescription = "background image",
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(modifier =Modifier.fillMaxSize().background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.background.copy(alpha = 0.55f),
                                MaterialTheme.colorScheme.background
                            ),
                        )
                    ))
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

        } ?: run {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error")
            }
        }
    }

}