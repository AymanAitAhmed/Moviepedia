package com.example.moviepedia.presentation.movieDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviepedia.components.GenreTypeCard
import com.example.moviepedia.components.getEquivalentGenre

@Composable
fun GenresLazyRow(
    genresList: List<Int>
) {

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        items(count = genresList.size, key = {
            genresList[it]
        }){id ->
            GenreTypeCard(title = getEquivalentGenre(id))
        }
    }
}