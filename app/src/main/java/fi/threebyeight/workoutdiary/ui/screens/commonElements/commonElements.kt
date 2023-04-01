package fi.threebyeight.workoutdiary.ui.screens.commonElements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
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
            .padding(bottom = 10.dp),
//            .background(Yellow),
        contentPadding = PaddingValues(horizontal = 13.dp, vertical = 13.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
//                .background(Cyan)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.button,
                modifier = Modifier.align(Alignment.Center)
            )
            Box(
                modifier = Modifier
//                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .background(White, RightArrowShape)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

private val RightArrowShape = GenericShape { size, _ ->
    lineTo(size.width, size.height / 2f)
    lineTo(0f, size.height)
    lineTo(size.width - 20f, size.height / 2f)
}