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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import fi.threebyeight.workoutdiary.Database.activitiesWithTypeNames
import fi.threebyeight.workoutdiary.R
import fi.threebyeight.workoutdiary.ui.screens.commonElements.ScreenTitle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun JournalScreen(data: List<activitiesWithTypeNames>) {
    Column {
        ScreenTitle(stringResource(R.string.titleJournal))
        JournalList(data)
    }
}

@Composable
fun JournalList(records: List<activitiesWithTypeNames>) {
    LazyColumn(
        modifier = Modifier.padding(8.dp)
    ) {
        items(records.reversed()) { record ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = record.activities.date,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
                Text(
                    text = record.type.name,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
                Text(
                    text = record.activities.duration.toString() + " min",
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )
            }
            Divider(color = Color.Transparent, thickness = 10.dp)
        }
    }
}