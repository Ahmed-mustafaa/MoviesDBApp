package banquemisr.challenge05.moviesdbapp.ViewModel

import banquemisr.challenge05.moviesdbapp.Screens.NetworkConnectionObserver
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import banquemisr.challenge05.moviesdbapp.MovieRepository.MovieRepository

class MoviesViewModelFactory(private val repository: MovieRepository,
                             private val networkObserver: NetworkConnectionObserver
) : ViewModelProvider.Factory {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
