package banquemisr.challenge05.moviesdbapp.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
data class Movie(val title: String,val releaseDate: String)


@Composable
fun NowPlayingScreen(navController: NavHostController) {
    val movies = remember {
        // this where the api template
        listOf(
            Movie("Movie 1", "2024-01-01"),
            Movie("Movie 2", "2024-01-05"),
            Movie("Movie 3", "2024-01-10")
        )
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(movies) { movie ->
            MovieItem(movie)
        }
    }
}
    @Composable
    fun MovieItem(movie: Movie) {
        Text(text = movie.title + " " + movie.releaseDate, ) // You can customize this to show more details
    }
