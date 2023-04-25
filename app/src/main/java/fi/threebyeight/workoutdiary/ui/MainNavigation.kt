package fi.threebyeight.workoutdiary.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fi.threebyeight.workoutdiary.R
import fi.threebyeight.workoutdiary.States.ActivityState
import fi.threebyeight.workoutdiary.States.TypeState
import fi.threebyeight.workoutdiary.ui.screens.*
import fi.threebyeight.workoutdiary.ui.theme.LightGrey

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavController(
    navController: NavHostController,
    setShowBottomBar: (Boolean) -> Unit,
    typeState: TypeState,
    activityState: ActivityState,
    viewModel: ViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "Workout"
    ) {
        composable(route = "Workout") {
            WorkoutScreen(navController)
            setShowBottomBar(true)
        }
        composable(route = "Journal") {
            JournalScreen()
            setShowBottomBar(true)
        }
        composable(route = "Plan") {
            PlanScreen()
            setShowBottomBar(true)
        }
        composable(route = "About") {
            AboutScreen()
            setShowBottomBar(true)
        }
        composable(route = "WorkoutNew") {
            WorkoutNewScreen(navController)
            setShowBottomBar(false)
        }
        composable(route = "WorkoutAdd") {
            WorkoutAddScreen(navController)
            setShowBottomBar(false)
        }
    }
}

@Composable
fun MainNavigation(items: List<TabItem>, navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    BottomNavigation(
        backgroundColor = LightGrey
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route)
                },
                icon = { Icon(item.icon, contentDescription = null) },
                selectedContentColor = MaterialTheme.colors.primaryVariant,
                unselectedContentColor = Color.Gray
            )
        }
    }
}

@Composable
fun DisabledMainNavigation() {
    BottomNavigation(
        backgroundColor = LightGrey
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.titleTopAppBar),
                style = MaterialTheme.typography.h2,
                color = Color.DarkGray
            )
        }
    }
}