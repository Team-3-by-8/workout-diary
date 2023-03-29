package fi.threebyeight.workoutdiary.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
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

data class activitiesWithTypeName(
    val type_name: String,
    val date: Date,
    val duration: Int,
    val max_HR: Int,
    val min_HR: Int,
    val average_HR: Int
)

@Dao
interface activitiesDao {
    @Insert(entity = activities::class)
    suspend fun insertNewActivity(date: Date, duration: Int, type_id: Int)

    //I wonder if I do it correctly
    @Insert(entity = activities::class)
    suspend fun insertNewActivityWithHR(activities: activities)

    @Query("SELECT * FROM Activities ORDER BY date ASC")
    fun getActivitiesByDate(): Flow<List<activities>>

    @Query(
        "SELECT activities.date as date," +
                "activities.duration as duration," +
                "activities.max_HR as max_HR," +
                "activities.min_HR as min_HR," +
                "activities.average_HR as average_HR," +
                "types.name as type_name" +
                "FROM activities, types" +
                "WHERE activities.type_id = types.type_id"
    )
    fun getActivitiesWithTypeNames(): Flow<List<activitiesWithTypeName>>
}
