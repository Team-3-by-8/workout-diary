package fi.threebyeight.workoutdiary.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WorkoutScreen() {
    Column {
        Text(
            text= "Record your workouts here!",
            modifier = Modifier.padding(top = 15.dp, bottom = 6.dp, start = 15.dp, end = 15.dp)
        )
    }
}