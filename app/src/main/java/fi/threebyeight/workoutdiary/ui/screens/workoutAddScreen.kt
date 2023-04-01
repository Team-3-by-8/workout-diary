package fi.threebyeight.workoutdiary.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import fi.threebyeight.workoutdiary.R
import fi.threebyeight.workoutdiary.ui.screens.commonElements.ScreenTitle

@Composable
fun WorkoutAddScreen(navController: NavController) {
    Column {
        ScreenTitle(stringResource(R.string.titleAddPrevious), navController, "Workout")
    }
}