package com.example.moviepedia.presentation.splashscreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.moviepedia.components.Screens
import com.example.moviepedia.presentation.movie_list_template.MovieListViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: MovieListViewModel
) {

    LaunchedEffect(key1 = 1) {
        viewModel.getCurrentLayoutType()
        Log.d("SplashScreen", "got the layout type")
        navController.navigate(Screens.ListsGraph.route)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "logo",
            modifier = Modifier.fillMaxSize(0.6f)
        )
    }

}