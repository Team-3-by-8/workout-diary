package fi.threebyeight.workoutdiary.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fi.threebyeight.workoutdiary.R
import fi.threebyeight.workoutdiary.States.ActivityState
import fi.threebyeight.workoutdiary.States.TypeState
import fi.threebyeight.workoutdiary.model.dummyWorkoutTypeList
import fi.threebyeight.workoutdiary.ui.screens.commonElements.ScreenTitle
import fi.threebyeight.workoutdiary.ui.screens.commonElements.SelectionMain
import fi.threebyeight.workoutdiary.viewmodel.WorkoutDiaryViewModel

@Composable
fun WorkoutNewScreen(navController: NavController, typeState: TypeState, activityState: ActivityState, viewModel: WorkoutDiaryViewModel) {
    Column {
        ScreenTitle(stringResource(R.string.titleStartNow), navController, "Workout")
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 32.dp)
        ) {
            SelectionMain(dummyWorkoutTypeList, RecordNew = true, navController, typeState = typeState, activityState, viewModel = viewModel)
        }
    }
}