package fi.threebyeight.workoutdiary.ui.screens.commonElements

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ScreenTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h2,
        modifier = Modifier.padding(top = 38.dp, start = 35.dp)
    )
}

@Composable
fun CommonButton(navController: NavController, title: String, route: String) {
    Button(
        onClick = { navController.navigate(route) },
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(bottom = 10.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.button)
    }
}