package com.example.moviepedia.presentation.movie_list_template

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.moviepedia.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    isLayoutGrid: Boolean,
    scrollBehavior: TopAppBarScrollBehavior,
    onSearchClick: () -> Unit,
    onChangeListLayoutClick: () -> Unit,
) {

    TopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            }

            IconButton(onClick = onChangeListLayoutClick) {
                Icon(
                    painter = painterResource(id = if (isLayoutGrid) R.drawable.baseline_grid_layout_24 else R.drawable.baseline_list_layout_24),
                    contentDescription = "change layout"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )

}