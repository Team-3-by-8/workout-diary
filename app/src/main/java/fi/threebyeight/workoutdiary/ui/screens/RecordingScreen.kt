package fi.threebyeight.workoutdiary.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fi.threebyeight.workoutdiary.Events.ActivityEvent
import fi.threebyeight.workoutdiary.ui.screens.commonElements.SaveRecordConfirmation
import fi.threebyeight.workoutdiary.ui.screens.commonElements.SelectionButton
import fi.threebyeight.workoutdiary.ui.screens.commonElements.WorkoutRecord
import fi.threebyeight.workoutdiary.ui.theme.LightGrey
import fi.threebyeight.workoutdiary.viewmodel.WorkoutDiaryViewModel

enum class State {
    RUNNING, PAUSED, CONFIRM_CANCEL, CONFIRM_SAVE
}

@Composable
fun RecordingScreen(
    navController: NavController,
    chosenWorkout: String,
    currentDate: String,
    viewModel: WorkoutDiaryViewModel
) {
    val workoutDuration = 602
    var seconds: Int = workoutDuration
    var minutes = 0
    while (seconds >= 60) {
        minutes += 1
        seconds -= 60
    }

    val displayedTimer = String.format("%02d", minutes) + ":" + String.format("%02d", seconds)
    var screenState by remember { mutableStateOf(State.RUNNING) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (screenState) {
            State.RUNNING -> {
                Text(chosenWorkout, style = MaterialTheme.typography.h6)
                Text(displayedTimer, fontSize = 80.sp)
                SelectionButton(
                    title = "Stop",
                    onClick = { screenState = State.PAUSED },
                    color = MaterialTheme.colors.primary,
                    width = 125
                )
            }
            State.PAUSED -> {
                Text(chosenWorkout, style = MaterialTheme.typography.h6)
                Text(displayedTimer, fontSize = 80.sp)
                SelectionButton(
                    title = "Continue",
                    onClick = { screenState = State.RUNNING },
                    color = LightGrey,
                    width = 125
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 80.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    SelectionButton(
                        title = "Cancel",
                        onClick = { screenState = State.CONFIRM_CANCEL },
                        color = MaterialTheme.colors.primaryVariant,
                        width = 100
                    )
                    Spacer(modifier = Modifier.weight(0.2f))
                    SelectionButton(
                        title = "Save",
                        onClick = { screenState = State.CONFIRM_SAVE },
                        width = 100
                    )
                }
            }
            State.CONFIRM_CANCEL -> {
                Text(chosenWorkout, style = MaterialTheme.typography.h6)
                Text(displayedTimer, fontSize = 80.sp)
                Spacer(modifier = Modifier.weight(1f))
                SaveRecordConfirmation(
                    "Delete the record?",
                    leftChoice = { screenState = State.PAUSED },
                    leftButtonText = "No",
                    leftButtonColor = MaterialTheme.colors.primaryVariant,
                    rightChoice = { navController.navigate("Workout") },
                    rightButtonText = "Yes",
                    rightButtonColor = MaterialTheme.colors.error
                )
            }
            State.CONFIRM_SAVE -> {
                WorkoutRecord(chosenWorkout, currentDate, minutes)
                Spacer(modifier = Modifier.weight(1f))
                SaveRecordConfirmation(
                    "Save the record?",
                    leftChoice = {
                        navController.navigate("Workout")
                        viewModel.onActivityEvent(ActivityEvent.SaveActivity)
                    },
                    rightChoice = { screenState = State.PAUSED },
                    rightButtonColor = MaterialTheme.colors.primaryVariant
                )
            }

        }
    }
}





