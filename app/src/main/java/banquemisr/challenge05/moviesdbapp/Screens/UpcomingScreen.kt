package banquemisr.challenge05.moviesdbapp.Screens

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import banquemisr.challenge05.moviesdbapp.ViewModel.MoviesViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun UpcomingScreen(navController: NavHostController, viewModel: MoviesViewModel){

    val movies by viewModel.upcomingMovies.collectAsState()
    LazyRow( modifier = Modifier.fillMaxSize().padding(top = 50.dp),
        contentPadding = PaddingValues(5.dp)
        ){
        items(movies) { movie ->

            MovieCard(movie,navController = navController) {
                // Navigation or other actions...
                navController.navigate("MovieDetailsScreen/${movie.id}")
            }
        androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.width(20.dp))
    }
    }

}