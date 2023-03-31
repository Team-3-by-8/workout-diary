package fi.threebyeight.workoutdiary.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import fi.threebyeight.workoutdiary.R
import fi.threebyeight.workoutdiary.ui.screens.commonElements.ScreenTitle

@Composable
fun AboutScreen() {
    Column {
        ScreenTitle(stringResource(R.string.titleAbout))
    }
}