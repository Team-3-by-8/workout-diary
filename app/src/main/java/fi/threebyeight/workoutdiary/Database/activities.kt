package fi.threebyeight.workoutdiary.Database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity
data class activities(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val date: java.sql.Date,
    //in minutes
    val duration: Int,
    //type, refers to id of type
    val type_id: Int,
    val max_HR: Int?,
    val min_HR: Int?,
    val average_HR: Int?
)

data class activitiesWithTypeNames(
    @Embedded val activities: activities,
    @Relation(
        parentColumn = "type_id",
        entityColumn = "id"
    )
    val type: type
)

@Dao
interface activitiesDao {
    @Insert(entity = activities::class)
    suspend fun insertActivity(activities: activities)

    //I wonder if I do it correctly

    @Query("SELECT * FROM Activities ORDER BY date ASC")
    fun getActivitiesByDate(): Flow<List<activities>>

    @Transaction
    @Query("SELECT * FROM activities")
    fun getActivities(): Flow<List<activitiesWithTypeNames>>
}
