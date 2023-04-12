package fi.threebyeight.workoutdiary.ui.screens.commonElements

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fi.threebyeight.workoutdiary.model.WorkoutType

@Composable
fun SelectionMain(types: List<WorkoutType>, RecordNew: Boolean) {
    var chosenWorkout by remember { mutableStateOf("") }

    Column {
        ScreenSubTitle("Select Workout")
        if (chosenWorkout.isEmpty()) {
            WorkoutTypeSelection(setChosenWorkout = { chosenWorkout = it }, types)
        } else {
            WorkoutConfirmation(chosenWorkout, setChosenWorkout = { chosenWorkout = it }, RecordNew)
        }
    }
}

@Composable
fun WorkoutTypeSelection(
    setChosenWorkout: (String) -> Unit,
    types: List<WorkoutType>
) {
    var workoutNameInput by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 70.dp)
            .padding(top = 17.dp)
            .fillMaxWidth()
    ) {
        item {
            AddNewWorkout(
                workoutNameInput = workoutNameInput,
                onValueChange = { workoutNameInput = it }
            )
        }
        items(types) { type ->
            SelectionButton(
                title = type.name,
                onClick = { setChosenWorkout(type.name) })
        }
    }
}

@Composable
fun AddNewWorkout(workoutNameInput: String, onValueChange: (String) -> Unit) {
    TextField(
        value = workoutNameInput,
        onValueChange = onValueChange,
        label = {
            Text(
                text = "Add New...",
                style = MaterialTheme.typography.button,
                color = Black,
                modifier = Modifier
            )
        },
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .scale(scaleY = 0.9F, scaleX = 1F)
            .height(54.dp)
            .border(width = 2.dp, Black)
    )
}

@Composable
fun InputBox(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    interactionSource: MutableInteractionSource
) {
    TextField(
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.button,
                color = Black,
                modifier = Modifier
            )
        },
        value = value,
        onValueChange = onValueChange,
        interactionSource = interactionSource,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Right),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .scale(scaleY = 0.9F, scaleX = 1F)
            .height(54.dp)
            .border(width = 2.dp, Black)
    )
}

@Composable
fun WorkoutConfirmation(
    chosenWorkout: String,
    setChosenWorkout: (String) -> Unit,
    RecordNew: Boolean
) {
    val bottomButtonTitle = if (RecordNew) "Start" else "Save"

    Column(
        modifier = Modifier
            .padding(horizontal = 70.dp)
            .padding(top = 17.dp)
            .fillMaxWidth()
    ) {
        SelectionButton(
            title = chosenWorkout,
            onClick = { setChosenWorkout("") }
        )
        if (RecordNew) {
            Text(text = "placeholder for HR button")
        } else {
            var dateInput by remember { mutableStateOf("12.04") }
            var durationInput by remember { mutableStateOf("30") }
            //var date = dateInput  // SHOULD BE DATE
            //var duration = durationInput.toIntOrNull() ?: 0
            val dateInteraction = remember { MutableInteractionSource() }
            val durationInteraction = remember { MutableInteractionSource() }

            InputBox(
                label = "Date",
                value = dateInput,
                onValueChange = { dateInput = it },
                interactionSource = dateInteraction.also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                dateInput = ""
                            }
                        }
                    }
                }
            )
            InputBox(
                label = "Duration",
                value = durationInput,
                onValueChange = { durationInput = it },
                interactionSource = durationInteraction.also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                durationInput = ""
                            }
                        }
                    }
                }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row {
            Spacer(modifier = Modifier.weight(1.5f))
            Box(modifier = Modifier.weight(1f)) {
                SelectionButton(title = bottomButtonTitle, onClick = { /*TODO*/ })
            }
        }
    }
}


