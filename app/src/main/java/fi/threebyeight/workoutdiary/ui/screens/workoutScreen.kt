package fi.threebyeight.workoutdiary.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fi.threebyeight.workoutdiary.R
import fi.threebyeight.workoutdiary.ui.screens.commonElements.PlanSummaryTable
import fi.threebyeight.workoutdiary.ui.screens.commonElements.ScreenTitle
import fi.threebyeight.workoutdiary.ui.screens.commonElements.SelectionButton
import fi.threebyeight.workoutdiary.ui.theme.OrangeBrown900
import fi.threebyeight.workoutdiary.ui.theme.Righteous
import fi.threebyeight.workoutdiary.ui.theme.StreakYellow

@Composable
fun WorkoutScreen(navController: NavController) {
    Column {
        ScreenTitle(stringResource(R.string.titleWorkout))
        Column(
            modifier = Modifier
                .padding(horizontal = 70.dp)
                .padding(top = 17.dp)
                .fillMaxWidth()
        ) {
            SelectionButton(
                title = stringResource(R.string.titleStartNow),
                onClick = { navController.navigate("WorkoutNew") },
                arrow = true
            )
            SelectionButton(
                title = stringResource(R.string.titleAddPrevious),
                onClick = { navController.navigate("WorkoutAdd") },
                arrow = true
            )
        }
        Divider(color = Color.Transparent, thickness = 10.dp)
        ScreenTitle(stringResource(R.string.titleRemainingPlan))
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 53.dp, vertical = 10.dp)
        ) {
            PlanSummaryTable()
        }
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .height(100.dp)
        ) {
            StreakCounter()
        }
    }
}

@Composable
fun StreakCounter() {
    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .wrapContentWidth(End)
            .fillMaxSize(),
        Arrangement.End
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Bottom)
                .width(140.dp)
                .height(70.dp)
                .background(StreakYellow, StreakShape)
                .shadow(
                    elevation = 2.dp,
                    ambientColor = OrangeBrown900,
                    spotColor = OrangeBrown900,
                    shape = StreakShape
                ),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom,
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = stringResource(R.string.streak),
                    style = TextStyle(
                        fontFamily = Righteous,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        letterSpacing = 1.sp,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(0.3f, 1.1f),
                            blurRadius = 1f
                        )
                    ),
                    modifier = Modifier.padding(end = 9.dp)
                )
                Text(
                    text = "30",
                    style = TextStyle(
                        fontSize = 24.sp,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(2f, 4f),
                            blurRadius = 2f
                        )
                    ),
                    modifier = Modifier.padding(end = 1.dp)
                )
            }
        }
    }
}

private val StreakShape = GenericShape { size, _ ->
    moveTo(size.width / 6f, size.height / 2f)
    lineTo(size.width, 0f)
    lineTo(size.width, size.height)
    lineTo(0f, size.height)
}

