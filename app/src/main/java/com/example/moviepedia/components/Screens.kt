package com.example.moviepedia.components

sealed class Screens(val route : String){

    object SplashScreen : Screens("Splash")
    object ListsGraph : Screens("graph")
    object TrendingScreen : Screens("Trending")
    object NowPlayingScreen : Screens("Now Playing")
    object PopularScreen : Screens("Popular")
    object TopRatedScreen : Screens("Top Rated")
    object UpComingScreen : Screens("Up Coming")
    object MovieDetailsScreen : Screens("movie")

}
