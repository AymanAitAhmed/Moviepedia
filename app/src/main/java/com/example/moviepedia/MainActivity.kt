package com.example.moviepedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviepedia.components.Screens
import com.example.moviepedia.data.localDb.movie.MovieEntity
import com.example.moviepedia.presentation.drawer.MyBottomNavBar
import com.example.moviepedia.presentation.movieDetails.MovieDetailsScreen
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
            MoviepediaTheme(dynamicColor = false) {
                // A surface container using the 'background' color from the theme
                val viewModel: MovieListViewModel = hiltViewModel()
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

                    val layoutType = viewModel.layoutType.collectAsStateWithLifecycle()

                    val popularMovies = viewModel.popularMovies.collectAsLazyPagingItems()

                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .nestedScroll(scrollBehavior.nestedScrollConnection),
                        topBar = {
                            if (!drawerTopAppBarEnabled) {
                                MyTopAppBar(
                                    title = "${currentScreen.value?.destination?.route}",
                                    isLayoutGrid = layoutType.value != 0,
                                    scrollBehavior = scrollBehavior,
                                    onSearchClick = { /*TODO*/ },
                                    onChangeListLayoutClick = {
                                        viewModel.flipLayoutType()
                                    }
                                )
                            }
                        },
                        bottomBar = {
                            if (!drawerTopAppBarEnabled) {
                                MyBottomNavBar(
                                    onBottomNavBarItemClick = {
                                        navController.navigate(it)
                                    }
                                )
                            }

                        },
                        containerColor = MaterialTheme.colorScheme.background
                    ) { paddingValues ->
                        Box(
                            modifier = Modifier.padding(paddingValues),
                            contentAlignment = Alignment.Center
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = Screens.SplashScreen.route
                            ) {

                                composable(
                                    route = Screens.SplashScreen.route,
                                    exitTransition = {
                                        slideOutOfContainer(
                                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                            animationSpec = tween(700, 20)
                                        )
                                    }) {
                                    SplashScreen(navController, initializingOperation = {
                                        viewModel.getCurrentLayoutType()
                                    })
                                }
                                composable(
                                    route = Screens.MovieDetailsScreen.route,
                                    enterTransition = {
                                        slideIntoContainer(
                                            towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                            animationSpec = tween(700)
                                        )
                                    },
                                    exitTransition = {
                                        slideOutOfContainer(
                                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                            animationSpec = tween(700)
                                        )
                                    },
                                    popEnterTransition = {
                                        slideIntoContainer(
                                            towards = AnimatedContentTransitionScope.SlideDirection.Left,
                                            animationSpec = tween(700)
                                        )
                                    },
                                    popExitTransition = {
                                        slideOutOfContainer(
                                            towards = AnimatedContentTransitionScope.SlideDirection.Right,
                                            animationSpec = tween(700)
                                        )
                                    }
                                ) {
                                    MovieDetailsScreen(
                                        onNavigateBackClick = {
                                            navController.popBackStack()
                                        },
                                        onSearchClick = {

                                        }
                                    )
                                }

                                listsGraph(navController = navController, layoutType, popularMovies)


                            }
                        }
                    }

                }
            }
        }
    }
}


fun NavGraphBuilder.listsGraph(
    navController: NavController,
    layoutType: State<Int>,
    popularMovies: LazyPagingItems<MovieEntity>
) {
    navigation(
        startDestination = Screens.TrendingScreen.route,
        route = Screens.ListsGraph.route
    ) {
        composable(route = Screens.TrendingScreen.route) {
            MovieList(layoutType.value, popularMovies, navController)
        }

        composable(route = Screens.PopularScreen.route) {
            MovieList(layoutType.value, popularMovies, navController)
        }

        composable(route = Screens.TopRatedScreen.route) {

        }

        composable(route = Screens.UpComingScreen.route) {

        }
    }
}
