package com.example.moviepedia.presentation.bottomnav

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import com.example.moviepedia.R
import com.example.moviepedia.components.Constants
import com.example.moviepedia.components.Screens
import com.example.moviepedia.ui.theme.redLight

@Composable
fun MyBottomNavBar(onBottomNavBarItemClick: (String) -> Unit) {

    val itemsList = listOf(
        MyBottomNavBarItem(
            title = Screens.PopularScreen.route,
            selectedIcon = R.drawable.baseline_popular_24,
            unselectedIcon = R.drawable.outline_popular_24
        ),
        MyBottomNavBarItem(
            title = Screens.NowPlayingScreen.route,
            selectedIcon = R.drawable.baseline_now_playing_24,
            unselectedIcon = R.drawable.baseline_now_playing_24
        ),
        MyBottomNavBarItem(
            title = Screens.UpComingScreen.route,
            selectedIcon = R.drawable.baseline_upcoming_24,
            unselectedIcon = R.drawable.outline_upcoming_24
        ),
        MyBottomNavBarItem(
            title = Screens.TopRatedScreen.route,
            selectedIcon = R.drawable.baseline_top_rated_24,
            unselectedIcon = R.drawable.outline_top_rated_24
        ),

        )

    NavigationBar (
        contentColor = MaterialTheme.colorScheme.onBackground,
        containerColor = MaterialTheme.colorScheme.surface
            ){

        itemsList.forEachIndexed { index, item ->
            val title = when(item.title){
                Screens.UpComingScreen.route -> Screens.UpComingScreen.route.takeLast(6)
                Screens.NowPlayingScreen.route -> Screens.NowPlayingScreen.route.takeLast(7)
                else -> item.title
            }
            val itemIsSelected = Constants.BOTTOM_NAV_SELECTED_INDEX== index
            NavigationBarItem(
                selected = itemIsSelected,
                onClick = {
                    Constants.BOTTOM_NAV_SELECTED_INDEX = index
                    onBottomNavBarItemClick(item.title)
                },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = if (itemIsSelected) item.selectedIcon else item.unselectedIcon
                        ),
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = title)
                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = redLight)
            )
        }
    }

}

data class MyBottomNavBarItem(
    val title: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
)