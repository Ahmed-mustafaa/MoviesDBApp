package banquemisr.challenge05.moviesdbapp.view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import banquemisr.challenge05.moviesdbapp.Model.Movie
import banquemisr.challenge05.moviesdbapp.ViewModel.MoviesViewModel
import coil.compose.rememberImagePainter


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun NowPlayingScreen(navController: NavController, viewModel: MoviesViewModel) {
    // Collect the StateFlow from the ViewModel

    val movies by viewModel.nowPlayingMovies.collectAsState()


    LazyRow(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(5.dp)
    ) {
        items(movies) { movie ->
            MovieCard(movie, navController)
            Spacer(modifier = Modifier.width(20.dp))
        }

    }
    }
    @Composable
    fun MovieCard(movie: Movie, navController: NavController) {
        Card(
            modifier = Modifier
                .height(200.dp)
                .width(350.dp)
                .clickable {
                    navController.navigate("movieDetails/${movie.title}")
                },

        ) {
            Row(modifier = Modifier.padding(16.dp)) {

                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                            .padding(end = 10.dp)
                    )
                    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Text(text = movie.title)
                        Text(text = movie.release_date)
/*
                        Text(text = movie.overview)
*/
                        Text(text = "Rating" + movie.vote_average.toString())
                        Log.i("MovieCard", "title:${movie.title} , overview:${movie.overview}," +
                                " average${movie.vote_average}, ${movie.overview}, releaseDate${movie.release_date}, " +
                                "PsserPath:${movie.poster_path},"
                                )
                    }
                }
            }
        }



