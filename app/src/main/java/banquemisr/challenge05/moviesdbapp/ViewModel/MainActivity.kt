package banquemisr.challenge05.moviesdbapp.ViewModel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import banquemisr.challenge05.moviesdbapp.Database.DatabaseBuilder
import banquemisr.challenge05.moviesdbapp.Model.Movie
import banquemisr.challenge05.moviesdbapp.MovieRepository.MovieRepository
import banquemisr.challenge05.moviesdbapp.Service.RetrofitClient
import banquemisr.challenge05.moviesdbapp.ui.theme.MoviesDBAppTheme
import banquemisr.challenge05.moviesdbapp.Screens.Nav
import banquemisr.challenge05.moviesdbapp.Screens.NetworkConnectionObserver
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var networkObserver: NetworkConnectionObserver
    private lateinit var viewModel: MoviesViewModel

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkObserver = NetworkConnectionObserver(applicationContext)

        setContent {
            val isConnected by networkObserver.networkStatus.collectAsState(initial = true)
                MoviesDBAppTheme {
                    val navController = rememberNavController()
                    val snackbarHostState = remember { SnackbarHostState() }
                    val movieDao = DatabaseBuilder.getInstance(applicationContext).movieDao()
                    val repository = MovieRepository(RetrofitClient.apiService, movieDao)
                    if (!isConnected) {
                        ConnectionStatusMessage("No internet connection!")
                        DisplayCachedMovies(viewModel)
                        viewModel = MoviesViewModelFactory(
                            repository,
                            networkObserver
                        ).create(MoviesViewModel::class.java)

                    } else {
                    viewModel = MoviesViewModelFactory(
                        repository,
                        networkObserver
                    ).create(MoviesViewModel::class.java)

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        snackbarHost = { SnackbarHost(snackbarHostState) }
                    ) { innerPadding ->

                        // Observe network connection and respond accordingly

                        Nav(
                            navController = navController,
                            viewModel = viewModel,
                            padding = innerPadding,
                            networkObserver
                        )
                    }
                }
            }
        }
        }

        @Composable
        @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
        private fun observeNetworkConnection(
            isConnected: Boolean,
            snackbarHostState: SnackbarHostState
        ) {
            val coroutineScope = rememberCoroutineScope()

            LaunchedEffect(isConnected) {
                if (isConnected) {
                    // Fetch movies from the API when connected
                    coroutineScope.launch {
                        viewModel.fetchNowPlayingMovies()
                        viewModel.getPopularMovies()
                        viewModel.getUpcomingMovies()
                        snackbarHostState.showSnackbar("You're back online!")
                    }
                } else {
                    // Fetch cached movies if no internet connection
                    coroutineScope.launch {
                        viewModel.getCachedMovies()
                        snackbarHostState.showSnackbar("No internet connection!")
                    }
                }
            }
        }

}
@Composable
fun ConnectionStatusMessage(message: String) {
    Text(
        text = message,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),  // Adjust padding as desired
        style = MaterialTheme.typography.bodyLarge // You can choose a different style or customize
    )
}    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
private fun DisplayCachedMovies(viewModel: MoviesViewModel) {
    val cachedMovies by viewModel.cachedMovies.collectAsState(initial = emptyList())
    Column(modifier = Modifier.padding(16.dp)) {
        for (movie in cachedMovies) {
            MovieCard(movie = movie)
        }
    }
}

@Composable
private fun MovieCard(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
            Text(text = movie.release_date, style = MaterialTheme.typography.bodyMedium)
            // Add more fields as necessary, e.g., synopsis, poster, etc.
        }
    }
}

    private fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork?.let {
        connectivityManager.getNetworkCapabilities(it)
    }
    return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
}
