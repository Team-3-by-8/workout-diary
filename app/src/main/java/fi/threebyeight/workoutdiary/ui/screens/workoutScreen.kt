package fi.threebyeight.workoutdiary.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.NavController

@Composable
fun WorkoutScreen(navController: NavController) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .height(45.dp)
    Text(
        text= "Workout",
        modifier = Modifier.padding(top = 15.dp, bottom = 6.dp, start = 15.dp, end = 15.dp)
    )
    Column(
        modifier = Modifier
            .padding(horizontal = 70.dp)
            .padding(top = 80.dp)
            .fillMaxWidth()
    ) {
        Button(
            onClick = { navController.navigate("WorkoutNew") },
            modifier = buttonModifier
        ) {
            Text(text = "Start workout")
        }
        Divider(color = Color.Transparent, thickness = 10.dp)
        Button(
            onClick = { navController.navigate("WorkoutAdd") },
            modifier = buttonModifier
        ) {
            Text(text = "Add previous")
        }
        Divider(color = Color.Transparent, thickness = 80.dp)
        Text(text = "Current plan")
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp)
//            .border(
//                border = BorderStroke(2.dp, MaterialTheme.colors.primary),
//                shape = Bo
//            )
        ) {
            Text(text = "This is some text...")
        }
    }
}