package com.example.moviepedia.presentation.drawer

import com.example.moviepedia.R

data class NavigationItem(
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val badgeCount: Int? = null
)

val drawerItems = listOf(
    NavigationItem(
        title = "Trending",
        selectedIcon = R.drawable.baseline_trending_24,
        unselectedIcon = R.drawable.outline_trending_24,
    ),
    NavigationItem(
        title = "Now Playing",
        selectedIcon = R.drawable.baseline_now_playing_24,
        unselectedIcon = R.drawable.baseline_now_playing_24,
    ),
    NavigationItem(
        title = "Popular",
        selectedIcon = R.drawable.baseline_popular_24,
        unselectedIcon = R.drawable.outline_popular_24,
    ),
    NavigationItem(
        title = "Top Rated",
        selectedIcon = R.drawable.baseline_top_rated_24,
        unselectedIcon = R.drawable.outline_top_rated_24,
    ),
    NavigationItem(
        title = "Up coming",
        selectedIcon = R.drawable.baseline_upcoming_24,
        unselectedIcon = R.drawable.outline_upcoming_24,
    ),
)
