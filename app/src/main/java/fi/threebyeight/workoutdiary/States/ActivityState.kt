package fi.threebyeight.workoutdiary.States

import fi.threebyeight.workoutdiary.Database.activitiesWithTypeNames
import fi.threebyeight.workoutdiary.ui.screens.commonElements.currentDate

data class ActivityState(
    val activities: List<activitiesWithTypeNames> = emptyList(),
    val date: String? = currentDate(), //maybe needs to be String, I am not sure what's best way for UI
    val type_id: Int = 0, // also not sure about these
    val duration: Int = 30,
    val max_HR: Int? = null,
    val min_HR: Int? = null,
    val average_HR: Int? = null,
    val showDialog: Boolean = false
)
