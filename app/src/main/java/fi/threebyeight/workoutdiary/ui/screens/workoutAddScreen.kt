package fi.threebyeight.workoutdiary.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fi.threebyeight.workoutdiary.R
import fi.threebyeight.workoutdiary.model.dummyWorkoutTypeList
import fi.threebyeight.workoutdiary.ui.screens.commonElements.ScreenTitle
import fi.threebyeight.workoutdiary.ui.screens.commonElements.SelectionMain

@Composable
fun WorkoutAddScreen(navController: NavController) {
    Column {
        ScreenTitle(stringResource(R.string.titleAddPrevious), navController, "Workout")
        Box(
            modifier = Modifier.weight(1f)
        ) {
            SelectionMain(dummyWorkoutTypeList)
        }
        Row(
            modifier = Modifier
                .padding(top = 0.dp)
                .fillMaxWidth()
                .height(80.dp)
        ) {
            StreakCounter()
        }
    }
}