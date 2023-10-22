package com.example.moviepedia.presentation.searchScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.moviepedia.R
import com.example.moviepedia.domain.model.MovieEntity
import com.example.moviepedia.presentation.movie_list_template.MovieList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    active : Boolean,
    onActiveChange: (Boolean) -> Unit,
    onBackPress : ()->Unit,
    onClearPress : () -> Unit,
    isLoading : Boolean,
    layoutType: Int,
    list: LazyPagingItems<MovieEntity>,
    navController: NavController
) {
    SearchBar(
        query = query,
        onQueryChange = {onQueryChange(it)},
        onSearch = {onSearch(it)},
        active = active,
        onActiveChange = {onActiveChange(it)},
        shape = RectangleShape,
        placeholder = {
            Text(text = "Ex: Bank heist movie")
        },
        leadingIcon = {
            IconButton(onClick = onBackPress) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Press")
            }
        },
        trailingIcon = {
            IconButton(onClick = {
                if (query.isBlank() || query.isEmpty()){
                    onBackPress()
                }else{
                    onClearPress()
                }
            }) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "clear Search")
            }
        }
    ) {
        if (isLoading){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                val lottieComposition = rememberLottieComposition(spec = LottieCompositionSpec.RawRes(
                    R.raw.animation_lnnc2so3))
                LottieAnimation(
                    composition = lottieComposition.value,
                    iterations = LottieConstants.IterateForever
                )
            }
        }else{
            MovieList(layoutType = layoutType, list = list, navController = navController)
        }
    }
}