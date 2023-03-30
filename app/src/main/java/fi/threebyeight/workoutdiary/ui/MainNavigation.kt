package fi.threebyeight.workoutdiary.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fi.threebyeight.workoutdiary.ui.screens.*
import fi.threebyeight.workoutdiary.ui.theme.LightGrey

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavController(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "Workout"
    ) {
        composable(route = "Workout") {
            WorkoutScreen()
        }
        composable(route = "Journal") {
            JournalScreen()
        }
        composable(route = "Plan") {
            PlanScreen()
        }
        composable(route = "About") {
            AboutScreen()
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
                icon = { Icon(item.icon, contentDescription = null) }
            )
        }
    }
}
