package com.example.moviepedia.presentation.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.moviepedia.R
import com.example.moviepedia.components.Screens

@Composable
fun SplashScreen(
    navController: NavController,
    initializingOperation: () -> Unit
) {

    LaunchedEffect(key1 = 1) {
        initializingOperation()
        navController.navigate(Screens.ListsGraph.route) {
            popUpTo(Screens.SplashScreen.route) {
                inclusive = true
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "app logo",
            modifier = Modifier.fillMaxSize(0.6f)
        )
    }

}