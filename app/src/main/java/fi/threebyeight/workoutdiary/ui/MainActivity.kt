package fi.threebyeight.workoutdiary.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import fi.threebyeight.workoutdiary.R
import fi.threebyeight.workoutdiary.ui.theme.WorkoutDiaryTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BasicLayout() {
    val navController = rememberNavController()
    val items = listOf(
        TabItem(ImageVector.vectorResource(id = R.drawable.workout), "Workout"),
        TabItem(ImageVector.vectorResource(id = R.drawable.journal), "Journal"),
        TabItem(ImageVector.vectorResource(id = R.drawable.plan), "Plan"),
        TabItem(ImageVector.vectorResource(id = R.drawable.about), "About")
    )
    var showBottomBar by remember { mutableStateOf(true) }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primaryVariant
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.titleTopAppBar),
                        style = MaterialTheme.typography.h1
                    )
                }
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                AppNavController(
                    navController = navController,
                    setShowBottomBar = { showBottomBar = it })
            }
        },
        bottomBar = {
            if (showBottomBar) {
                MainNavigation(items, navController)
            } else {
                DisabledMainNavigation()
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkoutDiaryTheme {
        BasicLayout()
    }
}