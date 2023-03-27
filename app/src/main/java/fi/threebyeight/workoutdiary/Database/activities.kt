package fi.threebyeight.workoutdiary.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.PrimaryKey
import java.util.Date

data class activities(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: Date,
    //in minutes
    val duration: Int,
    //type, refers to id of type
    val type_id: Int,
    val max_HR: Int?,
    val min_HR: Int?,
    val average_HR: Int?
)

@Dao
interface activitiesDao{
    @Insert(entity = activities::class)
    fun insertNewActivity(date: Date, duration: Int, type_id: Int )

    @Insert(entity = activities::class)
    fun insertNewActivityWithHR(date: Date, duration: Int, type_id: Int, max_HR: Int, min_HR: Int, average_HR: Int )
}
