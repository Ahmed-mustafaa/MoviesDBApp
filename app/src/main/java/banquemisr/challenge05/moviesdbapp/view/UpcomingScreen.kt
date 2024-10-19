package banquemisr.challenge05.moviesdbapp.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import banquemisr.challenge05.moviesdbapp.ViewModel.MoviesViewModel

@Composable
fun UpcomingScreen(navController: NavController, viewModel: MoviesViewModel){

    val movies by viewModel.upcomingMovies.collectAsState()
    LazyRow( modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(5.dp)
        ){
        items(movies) { movie ->
        MovieCard(movie, navController)
        androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.ui.Modifier.width(20.dp))
    }
    }

}