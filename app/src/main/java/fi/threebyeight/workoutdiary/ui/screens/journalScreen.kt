package fi.threebyeight.workoutdiary.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fi.threebyeight.workoutdiary.model.DummyRecord
import fi.threebyeight.workoutdiary.model.dummyData
import fi.threebyeight.workoutdiary.ui.screens.commonElements.ScreenTitle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun JournalScreen() {
    Column {
        ScreenTitle("Journal")
        JournalList(dummyData)
    }
}

@Composable
fun JournalList(records: List<DummyRecord>) {
    LazyColumn (
        modifier = Modifier.padding(8.dp)
    ){
        items(records) { record->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = record.date.toString(),
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
                Text(
                    text = record.workout,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
                Text(
                    text = record.minutes.toString() + " min",
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
            }
            Divider(color = Color.Transparent, thickness = 10.dp)
        }
    }
}