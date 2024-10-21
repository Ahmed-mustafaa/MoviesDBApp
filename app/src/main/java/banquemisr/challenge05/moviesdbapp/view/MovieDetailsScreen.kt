package banquemisr.challenge05.moviesdbapp.view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.platform.LocalConfiguration

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import banquemisr.challenge05.moviesdbapp.Model.Database.Movie
import banquemisr.challenge05.moviesdbapp.Model.Database.MovieEntity
import banquemisr.challenge05.moviesdbapp.ViewModel.MoviesViewModel
import coil.compose.rememberImagePainter

/*@Composable
fun MovieDetailsDialog(movie: banquemisr.challenge05.moviesdbapp.Model.Database.Movie, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.displayMedium)
                Text(text = movie.overview)
                // Other movie details...
                Button(onClick = onDismiss) {
                    Text("Close")
                }
            }
        }
    }
}*/@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable

fun MovieDetailsScreen(navController: NavHostController,  movie: Movie?,viewModel: MoviesViewModel) {

    val movieId = rememberSaveable { mutableStateOf(movie?.id) }

    LaunchedEffect(movieId.value) {
        movieId.value?.let {
            viewModel.getMovieById(it)
        }
    }
    val movieDetails by viewModel.movieDetails.collectAsState()

    movieDetails?.let { movie ->


/*
    Log.d("MovieDetailsScreen", "Movie ID: ${movie.g}")
*/

        // Create a scroll state to track scrolling
        val listState = rememberLazyListState()
        val landscapeMode = LandscapeMode()

        // Adjust the image height based on the scroll position
        val imageHeight =
            if (landscapeMode) (1000 - listState.firstVisibleItemScrollOffset / 3).coerceIn(
                700, 800).dp
            else (1000 - listState.firstVisibleItemScrollOffset / 4).coerceIn(700, 800).dp
         val paddingValue = if (landscapeMode) 20.dp else 0.dp
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
        ) {
            // LazyColumn for scrollable content
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(top = 20.dp)
            ) {
                item {
                    // banquemisr.challenge05.moviesdbapp.Model.Database.Movie Cover Image (resizable based on scroll)
                    Image(
                        painter = rememberImagePainter(
                            data = "https://image.tmdb.org/t/p/w500${movie?.poster_path}"
                        ),
                        contentDescription = "Movie Cover",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(paddingValue)
                            .padding(top = 25.dp)
                            .height(imageHeight)
                    )
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .background(Color.White)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = "Overview:",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.Black,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )

                        Text(
                            text = movie.overview ?: "No overview available",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = "Language: ${movie.original_language}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Rating: ${movie.vote_average}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )
                            }

                            Column {
                                Text(
                                    text = "Genre: ${movie.genres[0].name}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Runtime: ${movie.runtime} mins",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )


                            }
                        } ?: run {
                            // Display error if movie is not found
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Movie not found",
                                    color = Color.Red,
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}





@Composable
fun LandscapeMode(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
}
