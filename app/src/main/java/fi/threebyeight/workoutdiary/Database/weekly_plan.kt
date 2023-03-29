package fi.threebyeight.workoutdiary.Database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity
data class weekly_plan(
    val type_id: Int,
    val duration: Int
)
data class weekly_planWithTypeNames(
    val type_name: String,
    val duration: Int
)

@Dao
interface weekly_planDao{
    @Insert(entity = weekly_plan::class)
    suspend fun insertNewWeekly_plan(weekly_plan: weekly_plan )

    @Delete
    suspend fun deleteWeekly_plan(weekly_plan: weekly_plan)

    @Query(
        "SELECT weekly_plan.duration AS duration, "+
                "types.name as type_name"+
                "FROM types, weekly_plan"+
                "WHERE activities.type_id = types.id"
    )
    fun getWeekly_planWithTypeName() : Flow<List<weekly_planWithTypeNames>>
}