package fi.threebyeight.workoutdiary.Database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity
data class weekly_plan(
    val type_id: Int,
    val duration: Int
)
data class weekly_planTypeName(
    val type_name: String,
    val duration: Int
)

@Dao
interface Weekly_planDao{
    @Insert(entity = weekly_plan::class)
    suspend fun insertNewWeekly_plan(type_id: Int, duration: Int )

    @Delete
    suspend fun deleteWeekly_planByTypeId(type_id: Int)

    @Query(
        "SELECT weekly_plan.duration AS duration, "+
                "types.name as type_name"+
                "FROM types, weekly_plan"+
                "WHERE activities.type_id = types.id"
    )
    fun getWeekly_planWithTypeName() : Flow<List<weekly_planTypeName>>
}