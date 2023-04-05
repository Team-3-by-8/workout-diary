package fi.threebyeight.workoutdiary.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import fi.threebyeight.workoutdiary.ui.theme.WorkoutDiaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutDiaryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BasicLayout()
                }
            }
        }
    }
}

@Composable
fun BasicLayout() {
    val navController = rememberNavController()
    val items = listOf(
        TabItem(Icons.Filled.Check, "Workout"),
        TabItem(Icons.Filled.List, "Journal"),
        TabItem(Icons.Filled.Edit, "Plan"),
        TabItem(Icons.Filled.Face, "About")
    )
    Scaffold(
        topBar = { TopAppBar {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Workout Diary", style = MaterialTheme.typography.h1)
            }
        }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
            ) { AppNavController(navController = navController) }
        },
        bottomBar = { MainNavigation(items, navController) }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkoutDiaryTheme {
        BasicLayout()
    }
}