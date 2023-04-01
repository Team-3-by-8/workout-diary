package fi.threebyeight.workoutdiary.ui.screens.commonElements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ScreenTitle(
    title: String,
    navController: NavController? = null,
    route: String? = null
) {
    Row(
        modifier = Modifier
            .padding(top = 38.dp, start = 35.dp)
            .height(30.dp)
            .padding(top = 0.dp, start = 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        if (navController != null && route != null) {
            ArrowButton(navController, route)
        }
        Text(
            text = title,
            style = MaterialTheme.typography.h2,
        )
    }
}

@Composable
fun ArrowButton(navController: NavController, route: String) {
    Button(
        onClick = { navController.navigate(route) },
        modifier = Modifier
            .padding(end = 7.dp)
            .height(30.dp)
            .aspectRatio(1f),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.onBackground),
        shape = LeftArrowShape
    ) {}
}

@Composable
fun CommonButton(navController: NavController, title: String, route: String) {
    Button(
        onClick = { navController.navigate(route) },
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(bottom = 10.dp),
        contentPadding = PaddingValues(horizontal = 13.dp, vertical = 13.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
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

private val LeftArrowShape = GenericShape { size, _ ->
    moveTo(size.width, 0f)
    lineTo(0f, size.height / 2f)
    lineTo(size.width, size.height)
    lineTo(0f + 20f, size.height / 2f)
}