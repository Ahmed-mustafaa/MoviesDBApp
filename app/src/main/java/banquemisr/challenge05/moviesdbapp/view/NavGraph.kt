package banquemisr.challenge05.moviesdbapp.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import banquemisr.challenge05.moviesdbapp.ViewModel.MoviesViewModel
@SuppressLint("StateFlowValueCalledInComposition")
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun Nav(navController: NavHostController, viewModel: MoviesViewModel, padding: PaddingValues, networkObserver: NetworkConnectionObserver) {
    val isConnected by networkObserver.networkStatus.collectAsState(initial = true)

    NavHost(
        navController = navController,

        startDestination = "MainScreen"
    ) {
        // MainScreen with padding
        composable("MainScreen") {
            MainScreen(navController = navController, viewModel = viewModel, modifier = Modifier.padding(padding),networkObserver)
        }

        composable("PopularScreen") {
            PopularScreen(navController = navController, viewModel = viewModel)
        }
        composable("UpComingScreen") {
            UpcomingScreen(navController = navController, viewModel = viewModel)
        }
        composable("UpcomingScreen") {
            NowPlayingScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            "MovieDetailsScreen/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            val selectedMovie = viewModel.nowPlayingMovies.value.find { it.id == movieId }
            MovieDetailsScreen(navController = navController, movie = selectedMovie, viewModel = viewModel)
        }
    }
}


/*
fun Nav(viewModel: MoviesViewModel){
    val NavController = rememberNavController()

    NavHost(navController = NavController, startDestination = "MainScreen"){
        composable("MainScreen") {
            MainScreen(navController = NavController, viewModel = viewModel)
        }

        // Now Playing screen
        composable("NowPlayingScreen") {
            NowPlayingScreen(navController = NavController, viewModel = viewModel)
        }

        // banquemisr.challenge05.moviesdbapp.Model.Database.Movie details screen with movieId as argument
     */
/*   composable("MovieDetailsScreen"
*//*
*/
/*
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
*//*
*/
/*
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            MovieDetailsScreen(*//*
*/
/*movieId = movieId, movies=viewModel.nowPlayingMovies.collectAsState().value*//*
*/
/*)
        }*//*

        composable("MovieDetailsScreen"){
            MovieDetailsScreen()
        }
    }
    }
*/
