package banquemisr.challenge05.moviesdbapp.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TabRow
import androidx.compose.material.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Preview(showBackground = true)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val tabs = listOf("Now Playing","Popular","Upcoming")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Scaffold(

    topBar = {
        TopAppBar(
            title = {
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Movies App", Modifier.padding(horizontal = 10.dp),
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray,
                        style = androidx.compose.material.MaterialTheme.typography.h6,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace
                    )
                }
                    },

        )
    },

        content = {
            Column(modifier =   Modifier.fillMaxSize()){
                TabRow(selectedTabIndex = selectedTabIndex ){
                    tabs.forEachIndexed {index,title ->

                        Tab(
                            text = { Text(title) },
                            selected = selectedTabIndex == index,
                            onClick = {
                                selectedTabIndex = index
                    }
                        )
                }
            }
                when (selectedTabIndex) {
                    0 -> NowPlayingScreen(navController)
                    1 -> PopularScreen(navController)
                    2 -> UpcomingScreen(navController)
                }
            }
        }
    )
}

