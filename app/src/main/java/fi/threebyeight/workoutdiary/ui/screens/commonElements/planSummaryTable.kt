package fi.threebyeight.workoutdiary.ui.screens.commonElements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fi.threebyeight.workoutdiary.model.dummyPlanForTesting

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    alignment: Alignment = Alignment.CenterStart
) {
    Box(
        Modifier
            .fillMaxSize()
            .weight(weight)
            .padding(bottom = 9.dp),
    ) {
        Text(
            text = text,
            Modifier.align(alignment)
        )
    }

}

// Text(text = "x1", style = MaterialTheme.typography.body2)

@Composable
fun PlanSummaryTable() {
    val data = dummyPlanForTesting
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant, PartialBoxShape)
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            items(data) {
                val (workout, instances, minutes) = it
                Row(Modifier.fillMaxWidth()) {
                    TableCell(
                        text = workout,
                        weight = .6f
                    )
                    TableCell(
                        text = if (instances == 0) {
                            "..."
                        } else {
                            "x$instances"
                        },
                        weight = .2f
                    )
                    TableCell(
                        text = if (minutes == 0) {
                            "..."
                        } else {
                            "$minutes min"
                        },
                        weight = .3f,
                        alignment = Alignment.CenterEnd
                    )
                }
            }
        }
    }
}

private val PartialBoxShape = GenericShape { size, _ ->
    lineTo(size.width / 1.4f, 0f)
    lineTo(size.width / 1.4f - 50f, 10f)
    lineTo(10f, 10f)
    lineTo(10f, size.height - 50f)
    lineTo(0f, size.height)
}