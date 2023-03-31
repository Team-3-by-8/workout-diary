package fi.threebyeight.workoutdiary.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.NavController
import fi.threebyeight.workoutdiary.ui.screens.commonElements.CommonButton
import fi.threebyeight.workoutdiary.ui.screens.commonElements.ScreenTitle

@Composable
fun WorkoutScreen(navController: NavController) {
    ScreenTitle("Workout")
    Column(
        modifier = Modifier
            .padding(horizontal = 70.dp)
            .padding(top = 80.dp)
            .fillMaxWidth()
    ) {
        CommonButton(navController, "Start Now", "WorkoutNew")
//        Divider(color = Color.Transparent, thickness = 10.dp)
        CommonButton(navController, "Add Previous", "WorkoutAdd")
        Divider(color = Color.Transparent, thickness = 100.dp)

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