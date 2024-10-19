package banquemisr.challenge05.moviesdbapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import banquemisr.challenge05.moviesdbapp.Model.Database.DatabaseBuilder
import banquemisr.challenge05.moviesdbapp.Model.MovieRepository.MovieRepository
import banquemisr.challenge05.moviesdbapp.Service.RetrofitClient
import banquemisr.challenge05.moviesdbapp.ViewModel.MoviesViewModel
import banquemisr.challenge05.moviesdbapp.ViewModel.MoviesViewModelFactory
import banquemisr.challenge05.moviesdbapp.ui.theme.MoviesDBAppTheme
import banquemisr.challenge05.moviesdbapp.view.MainScreen

class MainActivity : ComponentActivity() {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            MoviesDBAppTheme {
                val navController = rememberNavController() // Initialize NavController
                val movieDao = DatabaseBuilder.getInstance(applicationContext).movieDao()
                val repository = MovieRepository(RetrofitClient.apiService, movieDao)
                val factory = MoviesViewModelFactory(repository)




                // Create ViewModel using the factory
                val viewModel: MoviesViewModel = MoviesViewModelFactory(repository).create(MoviesViewModel::class.java)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        navController = navController,
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    ) // Add MainScreen

                }

            }

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoviesDBAppTheme {
        Greeting("Android")
    }
}