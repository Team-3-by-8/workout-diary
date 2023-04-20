package fi.threebyeight.workoutdiary.States

import fi.threebyeight.workoutdiary.Database.activities
import java.sql.Date

data class ActivityState(
    val activities: List<activities> = emptyList(),
    val date: Date? = null, //maybe needs to be String, I am not sure what's best way for UI
    val type_id: Int = 0, // also not sure about these
    val duration: Int = 0,
    val max_HR: Int? = null,
    val min_HR: Int? = null,
    val average_HR: Int? = null
)
