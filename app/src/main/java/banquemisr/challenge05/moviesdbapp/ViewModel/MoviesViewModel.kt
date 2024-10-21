package banquemisr.challenge05.moviesdbapp.ViewModel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banquemisr.challenge05.moviesdbapp.Model.Database.MovieEntity
import banquemisr.challenge05.moviesdbapp.Model.Database.Movie
import banquemisr.challenge05.moviesdbapp.Model.MovieRepository.MovieRepository
import banquemisr.challenge05.moviesdbapp.Service.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class MoviesViewModel (private val repository: MovieRepository,
                       ): ViewModel() {
    private val _nowPlayingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val nowPlayingMovies: MutableStateFlow<List<Movie>> get() = _nowPlayingMovies
    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: MutableStateFlow<List<Movie>> get() = _popularMovies

    val _upcomingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val upcomingMovies: MutableStateFlow<List<Movie>> get() = _upcomingMovies


    private val _movieDetails = MutableStateFlow<MovieEntity?>(null)
    val movieDetails: MutableStateFlow<MovieEntity?> get() = _movieDetails


    init {
        fetchNowPlayingMovies()
        getPopularMovies()
        getUpcomingMovies()
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            repository.getUpComingMovies(RetrofitClient.API_KEY).collect {
                _upcomingMovies.value = it
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            repository.getNowPlayingMovies(RetrofitClient.API_KEY).collect { movies ->
                _nowPlayingMovies.value = movies
            }
        }
    }


    fun getPopularMovies() {
        viewModelScope.launch {
            repository.getPopularMovies(RetrofitClient.API_KEY).collect { movies ->
                _popularMovies.value = movies
            }
        }
    }

    fun getMovieById(movieId: Int?) {
        viewModelScope.launch {

            repository.getMovieDetailsByID(movieId, RetrofitClient.API_KEY).collect { movie ->
                _movieDetails.value = movie
            }

        }
    }
}