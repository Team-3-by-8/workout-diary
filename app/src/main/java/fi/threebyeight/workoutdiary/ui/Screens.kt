package fi.threebyeight.workoutdiary.ui

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

@Composable
fun JournalScreen() {
    Column {
        Text(
            text= "Your workout journal",
            modifier = Modifier.padding(top = 15.dp, bottom = 6.dp, start = 15.dp, end = 15.dp)
        )
    }
}

@Composable
fun PlanScreen() {
    Column {
        Text(
            text= "Your workout plan",
            modifier = Modifier.padding(top = 15.dp, bottom = 6.dp, start = 15.dp, end = 15.dp)
        )
    }
}

@Composable
fun AboutScreen() {
    Column {
        Text(
            text= "About, Settings, etc.",
            modifier = Modifier.padding(top = 15.dp, bottom = 6.dp, start = 15.dp, end = 15.dp)
        )
    }
}