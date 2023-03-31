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
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fi.threebyeight.workoutdiary.ui.screens.commonElements.CommonButton
import fi.threebyeight.workoutdiary.ui.screens.commonElements.PlanSummaryTable
import fi.threebyeight.workoutdiary.ui.screens.commonElements.ScreenTitle
import fi.threebyeight.workoutdiary.ui.theme.StreakYellow

@Composable
fun WorkoutScreen(navController: NavController) {
    Column {
        ScreenTitle("Workout")
        Column(
            modifier = Modifier
                .padding(horizontal = 70.dp)
                .padding(top = 30.dp)
                .fillMaxWidth()
        ) {
            CommonButton(navController, "Start Now", "WorkoutNew")
//        Divider(color = Color.Transparent, thickness = 10.dp)
            CommonButton(navController, "Add Previous", "WorkoutAdd")
        }
        Divider(color = Color.Transparent, thickness = 10.dp)
        ScreenTitle("Remaining Plan")
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 53.dp, vertical = 10.dp)
//                .background(Color.Cyan)
//                .border(
//                    border = BorderStroke(3.dp, MaterialTheme.colors.primaryVariant),
//                )
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
                .background(StreakYellow, StreakShape),
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
                    text = "Streak  ", fontSize = 14.sp, style = MaterialTheme.typography.h2
                )
                Text(
                    text = "30", fontSize = 24.sp
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

