package banquemisr.challenge05.moviesdbapp.Screens

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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

