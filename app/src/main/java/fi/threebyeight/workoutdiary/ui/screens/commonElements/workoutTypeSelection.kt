package fi.threebyeight.workoutdiary.ui.screens.commonElements

import androidx.compose.foundation.border
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fi.threebyeight.workoutdiary.model.WorkoutType

@Composable
fun WorkoutTypeSelection(types: List<WorkoutType>) {
    var workoutNameInput by remember { mutableStateOf("") }

    ScreenSubTitle("Select Workout")
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 70.dp)
            .padding(top = 17.dp)
            .fillMaxWidth()
    ) {
        item {
//            CommonButton(title = "Add New Workout...", color = Color.LightGray)
            AddNewWorkout(
                workoutNameInput = workoutNameInput,
                onValueChange = { workoutNameInput = it })

        }
        items(types) { type ->
            CommonButton(title = type.name)
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
//        placeholder = { Text(text = "placeholder!")},
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
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
            .border(width = 2.dp, Color.Black)
    )
}