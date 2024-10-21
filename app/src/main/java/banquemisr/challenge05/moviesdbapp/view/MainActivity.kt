package banquemisr.challenge05.moviesdbapp.view

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import banquemisr.challenge05.moviesdbapp.Model.Database.DatabaseBuilder
import banquemisr.challenge05.moviesdbapp.Model.MovieRepository.MovieRepository
import banquemisr.challenge05.moviesdbapp.Service.RetrofitClient
import banquemisr.challenge05.moviesdbapp.ViewModel.MoviesViewModel
import banquemisr.challenge05.moviesdbapp.ViewModel.MoviesViewModelFactory
import banquemisr.challenge05.moviesdbapp.ui.theme.MoviesDBAppTheme

class MainActivity : ComponentActivity() {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MoviesDBAppTheme {
                val navController = rememberNavController() // Create navController here

                // Initialize NavController
                val movieDao = DatabaseBuilder.getInstance(applicationContext).movieDao()
                val repository = MovieRepository(RetrofitClient.apiService, movieDao)
/*
                val factory = MoviesViewModelFactory(repository)
*/
                val viewModel: MoviesViewModel = MoviesViewModelFactory(repository).create(MoviesViewModel::class.java)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Nav(navController = navController, viewModel = viewModel, padding = innerPadding)

                }

            }

        }

    }
}


