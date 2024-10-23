package banquemisr.challenge05.moviesdbapp.view

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

import banquemisr.challenge05.moviesdbapp.ViewModel.MoviesViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun PopularScreen (navController: NavHostController,viewModel: MoviesViewModel) {
    val popularMovies by viewModel.popularMovies.collectAsState()
    LazyRow(

        modifier = Modifier.fillMaxSize().padding(top = 50.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
            items(popularMovies) { movie ->
                MovieCard(movie,navController=navController) {
                    // Pass only the movieId to the details screen
                    navController.navigate("MovieDetailsScreen/${movie.id}")
                }
                Spacer(modifier = Modifier.width(20.dp))        }
    }

}
/*
fun getMockMovies(): List<banquemisr.challenge05.moviesdbapp.Model.Database.Movie> {
    return listOf(
    banquemisr.challenge05.moviesdbapp.Model.Database.Movie(1,
        "banquemisr.challenge05.moviesdbapp.Model.Database.Movie 1",
        "2022-10-01", "/poster1.jpg", "Description 1","8.5",8.5,
    ),
        banquemisr.challenge05.moviesdbapp.Model.Database.Movie(2,
        "banquemisr.challenge05.moviesdbapp.Model.Database.Movie 2",
        "2022-10-02", "/poster2.jpg", "Description 2","7.8",7.8,
    ),
        MovieEntity(3,
        "banquemisr.challenge05.moviesdbapp.Model.Database.Movie 3",
        "2022-10-03", "/poster3.jpg", "Description 3","9.2",9.2,
    ),
    )

}
*/

/*
fun PreviewPopularScreen() {
    // Use mock data instead of ViewModel data for the preview
*/
/*
    val popularMovies = getMockMovies()
*//*

    val navController = rememberNavController()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(popularMovies) { movie ->
            MovieCard(movie, navController)
        }
    }
}
*/






