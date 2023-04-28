package fi.threebyeight.workoutdiary.Events

import fi.threebyeight.workoutdiary.Database.activities
import fi.threebyeight.workoutdiary.Database.type
import java.sql.Date

sealed interface ActivityEvent {
    object SaveActivity : ActivityEvent
    data class setDate(val date: String) : ActivityEvent
    data class setDuration(val duration: Int) : ActivityEvent
    data class setType_id(val type_id: Int) : ActivityEvent
    data class setMax_HR(val max_HR: Int) : ActivityEvent
    data class setMin_HR(val min_HR: Int) : ActivityEvent
    data class setAverage_HR(val average_HR: Int) : ActivityEvent
    object ShowDialog:ActivityEvent
    object HideDialog:ActivityEvent
    object deleteActivity:ActivityEvent
}
