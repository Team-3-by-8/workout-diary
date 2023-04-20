package fi.threebyeight.workoutdiary.Database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity
data class weekly_plan(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val type_id: Int,
    val duration: Int
)

data class weekly_planWithTypeNames(
    @Embedded val weekly_plan: weekly_plan,
    @Relation(
        parentColumn = "type_id",
        entityColumn = "id"
    )
    val type: type
)

@Dao
interface weekly_planDao {
    @Insert(entity = weekly_plan::class)
    suspend fun insertNewWeekly_plan(weekly_plan: weekly_plan)

    @Delete
    suspend fun deleteWeekly_plan(weekly_plan: weekly_plan)

    /* @Query(
         "SELECT weekly_plan.duration AS duration, "+
                 "types.name AS type_name"+
                 "FROM types, weekly_plan"+
                 "WHERE activities.type_id = types.id"
     )
     fun getWeekly_planWithTypeName() : Flow<List<weekly_planWithTypeNames>>*/
    @Transaction
    @Query(
        "SELECT * FROM weekly_plan"
    )
    fun getWeekly_planWithTypeName(): Flow<List<weekly_planWithTypeNames>>
}