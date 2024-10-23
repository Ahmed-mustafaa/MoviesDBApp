package banquemisr.challenge05.moviesdbapp.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import banquemisr.challenge05.moviesdbapp.ViewModel.MoviesViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MoviesViewModel,
    modifier: Modifier = Modifier,
    networkObserver: NetworkConnectionObserver,
) {
    val coroutineScope = rememberCoroutineScope()
    val isConnected by networkObserver.networkStatus.collectAsState(initial = true)

    // Fetch cached movies on startup if not connected
    LaunchedEffect(isConnected) {
        if (!isConnected) {
            viewModel.getCachedMovies()
        }
    }

    val tabs = listOf("Now Playing", "Popular", "Upcoming")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movies App")

                }
            )
        },
        content = {
            Column(modifier = modifier.fillMaxSize()
                .padding(top = 10.dp)) {
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier.fillMaxWidth()
                        .height(120.dp)
                        .padding(top = 80.dp),
                    containerColor = MaterialTheme.colorScheme.surface,

                    ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title,
                                fontSize = 18.sp, // Increase the font size

                                modifier = Modifier.fillMaxWidth(), // Fill the available width
                                // Add vertical padding for better appearance


                                color = if (selectedTabIndex == index)
                                    Color.Black // Highlight color for selected tab
                                else
                                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                            },
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

