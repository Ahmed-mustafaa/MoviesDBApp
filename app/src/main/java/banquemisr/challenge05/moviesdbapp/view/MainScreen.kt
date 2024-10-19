package banquemisr.challenge05.moviesdbapp.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import banquemisr.challenge05.moviesdbapp.ViewModel.MoviesViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MoviesViewModel,
    modifier: Modifier = Modifier
) {
    val tabs = listOf("Now Playing", "Popular", "Upcoming")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movies App") }
            )
        },
        content = {
            Column(modifier = modifier.fillMaxSize()) {
                TabRow(selectedTabIndex = selectedTabIndex , modifier.fillMaxWidth()
                    .height(100.dp)
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index }
                        )
                    }
                }

                // Display the correct screen based on selected tab
                when (selectedTabIndex) {
                    0 -> NowPlayingScreen(navController, viewModel)
                    1 -> PopularScreen(navController, viewModel)
                    2 -> UpcomingScreen(navController, viewModel)
                }
            }
        }
    )
}
