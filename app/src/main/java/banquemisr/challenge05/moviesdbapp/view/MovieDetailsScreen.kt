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
    import androidx.compose.foundation.layout.wrapContentHeight
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.LazyRow
    import androidx.compose.foundation.lazy.rememberLazyListState
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.Add
    import androidx.compose.material.icons.filled.ArrowBack
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
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
    import androidx.compose.ui.draw.alpha
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.graphics.Brush
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
    }*/@OptIn(ExperimentalMaterial3Api::class)
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    @Composable

    fun MovieDetailsScreen(navController: NavHostController,  movie: Movie?,viewModel: MoviesViewModel) {

        val movieId = rememberSaveable { mutableStateOf(movie?.id) }

        LaunchedEffect(movieId.value) {
            movieId.value?.let {
                viewModel.getMovieById(it)
            }
        }

        val movieDetails by viewModel.movieDetails.collectAsState()
        // TopAppBar with a Back button



            movieDetails?.let { movie ->


                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {

                    BackGroundPoster(movie)
                    ForegroundPoster(movie)
                    Column(modifier = Modifier.fillMaxSize()) {
                        // TopAppBar with a Back button
                        TopAppBar(
                            title = { Text(text = "Movie Details") },
                            navigationIcon = {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack, // Ensure this is the back arrow
                                        contentDescription = "Back",
                                        tint = Color.Black
                                    )
                                }
                            },
                            modifier = Modifier.background(Color.Red)
                        )
                    }
                    Column(
                        Modifier
                            .padding(start = 20.dp, end = 20.dp, bottom = 50.dp)
                            .align(Alignment.BottomCenter),
                        verticalArrangement = Arrangement.spacedBy(20.dp),

                        ) {


                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .background(Color(0xFFEDDFE0))
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
                                        text = "Genre: ${movie.genres?.get(0)?.name}",
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






        @Composable
        fun LandscapeMode(): Boolean {
            val configuration = LocalConfiguration.current
            return configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
        }

        @Composable
        fun ForegroundPoster(movie: MovieEntity) {
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(250.dp)
                    .padding(top = 80.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.TopCenter
            ) {


                Image(
                    painter = rememberImagePainter(
                        data = "https://image.tmdb.org/t/p/w500${movie.poster_path}"  // Using the poster path from MovieEntity
                    ),
                    contentDescription = movie.title,  // The title for content description
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(250.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.Transparent,
                                    Color(0xB91A1B1B),
                                )
                            ), shape = RoundedCornerShape(16.dp)
                        )
                )
            }
        }

        @Composable
        fun BackGroundPoster(movie: MovieEntity) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray)
            ) {
                // Display the movie poster in the background
                Image(
                    painter = rememberImagePainter(
                        data = "https://image.tmdb.org/t/p/w500${movie.poster_path}"  // Load poster image using the movie's poster path
                    ),
                    contentDescription = movie.title,  // Movie title for accessibility
                    contentScale = ContentScale.Crop,  // Crop the image to fill the background
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(0.6f)  // Semi-transparent background image
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.DarkGray
                                )
                            )
                        )
                )
            }
        }


