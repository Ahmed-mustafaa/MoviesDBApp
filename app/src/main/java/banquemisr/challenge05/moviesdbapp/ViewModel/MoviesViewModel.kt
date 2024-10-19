package banquemisr.challenge05.moviesdbapp.ViewModel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banquemisr.challenge05.moviesdbapp.Model.Database.MovieEntity
import banquemisr.challenge05.moviesdbapp.Model.Movie
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

init {
    fetchNowPlayingMovies()
    getPopularMovies()
}

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun fetchNowPlayingMovies() {
        viewModelScope.launch {
            repository.getNowPlayingMovies(RetrofitClient.API_KEY).collect { movies ->
                _nowPlayingMovies.value = movies
            }
        }
    }
    private fun insertMovie(movie: Movie) {
        viewModelScope.launch {
            repository.insertMovie(movie)
        }
    }
        fun getPopularMovies() {
            viewModelScope.launch {
                repository.getPopularMovies(RetrofitClient.API_KEY).collect { movies ->
                    _popularMovies.value = movies
                }
            }
        }


}