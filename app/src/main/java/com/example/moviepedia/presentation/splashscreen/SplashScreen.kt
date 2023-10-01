package com.example.moviepedia.presentation.splashscreen

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

@Composable
fun SplashScreen(
    navController: NavController,
    initializingOperation: () -> Unit
    ) {

    LaunchedEffect(key1 = 1) {
        initializingOperation()
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