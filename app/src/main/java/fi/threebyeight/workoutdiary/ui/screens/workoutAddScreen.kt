package fi.threebyeight.workoutdiary.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fi.threebyeight.workoutdiary.R
import fi.threebyeight.workoutdiary.States.TypeState
import fi.threebyeight.workoutdiary.model.dummyWorkoutTypeList
import fi.threebyeight.workoutdiary.ui.screens.commonElements.ScreenTitle
import fi.threebyeight.workoutdiary.ui.screens.commonElements.SelectionMain

@Composable
fun WorkoutAddScreen(navController: NavController, typeState: TypeState) {
    Column {
        ScreenTitle(stringResource(R.string.titleAddPrevious), navController, "Workout")
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 32.dp)
        ) {
            SelectionMain(dummyWorkoutTypeList, RecordNew = false, typeState)
        }
    }
}