package banquemisr.challenge05.moviesdbapp.view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import banquemisr.challenge05.moviesdbapp.Model.Database.Movie
import banquemisr.challenge05.moviesdbapp.ViewModel.MoviesViewModel
import coil.compose.rememberImagePainter


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun NowPlayingScreen(navController: NavHostController, viewModel: MoviesViewModel) {
    // Collect the StateFlow from the ViewModel

    val movies by viewModel.nowPlayingMovies.collectAsState()
    val Cachedmovies by viewModel.cachedMovies.collectAsState()


    LazyRow(
        modifier = Modifier.fillMaxSize().padding(top = 50.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(movies) { movie ->
            MovieCard(movie = movie, navController = navController) {
                Log.d("NowPlayingScreen", "Navigating to MovieDetailsScreen")

                navController.navigate("MovieDetailsScreen/${movie.id}")

            }
            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}
    @Composable
    fun MovieCard(movie: Movie,  navController: NavHostController, onClick: () -> Unit) {

        Card(
            modifier = Modifier
                .height(350.dp)
                .width(250.dp)
                .clickable {
                    onClick()
                },
            elevation = CardDefaults.cardElevation(8.dp)

        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally // Align content to the center
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(200.dp)
                        .height(250.dp)
                        .padding(top = 1.dp)
                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))

                )
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    Text(text = movie.title, style = MaterialTheme.typography.titleMedium )
                    Text(text = movie.release_date, style = MaterialTheme.typography.bodyMedium)
                    /*
                Text(text = movie.overview)
*/
                    Text(text = "Rating" + movie.vote_average.toString(),style = MaterialTheme.typography.bodyMedium)
                    Log.i(
                        "MovieCard", "title:${movie.title} , overview:${movie.overview}," +
                                " average${movie.vote_average}, ${movie.overview}, releaseDate${movie.release_date}, " +
                                "PsserPath:${movie.poster_path}," + movie.runtime
                    )
                }
            }
        }
    }





