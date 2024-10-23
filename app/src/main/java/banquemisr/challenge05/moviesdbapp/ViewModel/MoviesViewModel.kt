package banquemisr.challenge05.moviesdbapp.ViewModel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banquemisr.challenge05.moviesdbapp.Model.MovieEntity
import banquemisr.challenge05.moviesdbapp.Model.Movie
import banquemisr.challenge05.moviesdbapp.MovieRepository.IMovieRepository
import banquemisr.challenge05.moviesdbapp.Service.RetrofitClient
import banquemisr.challenge05.moviesdbapp.Service.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class MoviesViewModel(private val repository: IMovieRepository,
): ViewModel() {
    private val _nowPlayingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val nowPlayingMovies: MutableStateFlow<List<Movie>> get() = _nowPlayingMovies
    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: MutableStateFlow<List<Movie>> get() = _popularMovies

    val _upcomingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val upcomingMovies: MutableStateFlow<List<Movie>> get() = _upcomingMovies

    private val _cachedMovies = MutableStateFlow<List<Movie>>(emptyList())
    val cachedMovies: StateFlow<List<Movie>> = _cachedMovies

    private val _movieDetails = MutableStateFlow<MovieEntity?>(null)
    val movieDetails: MutableStateFlow<MovieEntity?> get() = _movieDetails


    init {
        viewModelScope.launch {
            repository.getCachedMovies().collect { movies ->
                _cachedMovies.value = movies
            }
        }
        fetchNowPlayingMovies()
        getPopularMovies()
        getUpcomingMovies()
    }
    /*
        private fun observeNetworkConnection() {
            viewModelScope.launch {
                networkObserver.networkStatus.collect { isConnected ->
                    if (isConnected) {
                        // If connected, fetch movies from the API
                        fetchNowPlayingMovies()
                        getPopularMovies()
                        getUpcomingMovies()
                    } else {
                        // If not connected, fetch cached movies
                        getCachedMovies()
                    }
                }
            }
        }
    */
    fun insertMovies(movies: List<Movie>) {
        viewModelScope.launch {
            try {
                repository.insertMovies(movies) // Call to the repository to insert movies
            } catch (e: Exception) {
                // Handle any errors here, e.g., log or show a Snackbar
                Log.e("MoviesViewModel", "Error inserting movies: ${e.message}")
            }
        }
    }

    fun getUpcomingMovies() {
        viewModelScope.launch {
            repository.getUpComingMovies(RetrofitClient.API_KEY).collect { movies->
                _upcomingMovies.value = movies
                repository.insertMovies(movies)

            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            repository.getNowPlayingMovies(RetrofitClient.API_KEY).collect { movies ->
                _nowPlayingMovies.value = movies
                repository.insertMovies(movies)
            }
        }
    }


    fun getPopularMovies() {
        viewModelScope.launch {
            repository.getPopularMovies(RetrofitClient.API_KEY).collect { movies ->
                _popularMovies.value = movies
                repository.insertMovies(movies)

            }
        }
    }

    fun insertMovieById(movie: Movie) {
        viewModelScope.launch {
            repository.insertMovie(movie)
        }
    }
    fun getMovieById(movieId: Int?) {
        viewModelScope.launch {
            repository.getMovieDetailsByID(movieId, Utils.API_KEY).collect { movie ->
                if (movie != null) {
                    _movieDetails.value = movie // Update your LiveData or StateFlow
                } else {
                    // Handle the case when the movie is not found or there's an error
                    _movieDetails.value = null
                }          }
        }
    }

    fun getCachedMovies() {
        viewModelScope.launch {
            repository.getCachedMovies().collect { movies ->
                // Update the state variable with the fetched cached movies
                _cachedMovies.value = movies
            }
        }
    }
}

