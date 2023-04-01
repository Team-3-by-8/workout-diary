package fi.threebyeight.workoutdiary.ui.screens.commonElements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
fun ScreenSubTitle(title: String) {
    Row(
        modifier = Modifier
            .padding(top = 33.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h2,
        )
    }
}

@Composable
fun CommonButton(
    title: String,
    navController: NavController? = null,
    route: String? = null,
    color: Color = MaterialTheme.colors.primary
) {
    Button(
        onClick = {
            if (navController != null && route != null) {
                navController.navigate(route)
            } else {
                /*TODO*/
            }
        },
        elevation = ButtonDefaults.elevation(
            defaultElevation = 10.dp,
            pressedElevation = 5.dp,
            disabledElevation = 0.dp,
        ),
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(bottom = 10.dp)
            .background(color)
            .shadow(elevation = 5.dp, ambientColor = Color.Black, spotColor = Color.Black),
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