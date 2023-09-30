package com.example.moviepedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.moviepedia.components.Screens
import com.example.moviepedia.data.LayoutType
import com.example.moviepedia.presentation.drawer.DrawerContent
import com.example.moviepedia.presentation.movie_list_template.MovieList
import com.example.moviepedia.presentation.movie_list_template.MovieListViewModel
import com.example.moviepedia.presentation.movie_list_template.MyTopAppBar
import com.example.moviepedia.presentation.splashscreen.SplashScreen
import com.example.moviepedia.ui.theme.MoviepediaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviepediaTheme {
                // A surface container using the 'background' color from the theme
                val movieListViewModel = viewModel<MovieListViewModel>()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    val currentScreen = navController.currentBackStackEntryAsState()
                    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
                    val drawerTopAppBarEnabled =
                        currentScreen.value?.destination?.route?.lowercase() == Screens.SplashScreen.route.lowercase()
                                || currentScreen.value?.destination?.route?.lowercase() == Screens.MovieDetailsScreen.route

                    val layoutType = movieListViewModel.layoutType.collectAsStateWithLifecycle()

                    ModalNavigationDrawer(
                        drawerContent = {
                            DrawerContent()
                        },
                        gesturesEnabled = !drawerTopAppBarEnabled
                    ) {
                        Scaffold(
                            modifier = Modifier
                                .fillMaxSize()
                                .nestedScroll(scrollBehavior.nestedScrollConnection),
                            topBar = {
                                if (!drawerTopAppBarEnabled){
                                    MyTopAppBar(
                                        title = "${currentScreen.value?.destination?.route}",
                                        isLayoutGrid = layoutType.value != 0,
                                        scrollBehavior = scrollBehavior,
                                        onShowDrawerClick = { /*TODO*/ },
                                        onSearchClick = { /*TODO*/ },
                                        onChangeListLayoutClick = movieListViewModel::flipLayoutType
                                    )
                                }
                            }
                        ) { paddingValues ->
                            Box(
                                modifier = Modifier.padding(paddingValues),
                                contentAlignment = Alignment.Center
                            ) {
                                NavHost(
                                    navController = navController,
                                    startDestination = Screens.SplashScreen.route
                                ) {

                                    composable(route = Screens.SplashScreen.route) {
                                        SplashScreen(navController, movieListViewModel)
                                    }
                                    composable(route = Screens.MovieDetailsScreen.route) {

                                    }

                                    listsGraph(navController = navController,layoutType)


                                }
                            }
                        }
                    }

                }
            }
        }
    }
}


fun NavGraphBuilder.listsGraph(navController: NavController,layoutType : State<Int>) {
    navigation(
        startDestination = Screens.TrendingScreen.route,
        route = Screens.ListsGraph.route
    ) {
        composable(route = Screens.TrendingScreen.route) {
            MovieList(layoutType.value)
        }

        composable(route = Screens.PopularScreen.route) {

        }

        composable(route = Screens.TopRatedScreen.route) {

        }

        composable(route = Screens.UpComingScreen.route) {

        }
    }
}
