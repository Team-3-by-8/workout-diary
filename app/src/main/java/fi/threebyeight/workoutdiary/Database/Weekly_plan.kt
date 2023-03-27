package fi.threebyeight.workoutdiary.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import java.util.*

@Entity
data class Weekly_plan(
    val type_id: Int,
    val duration: Int
)

@Dao
interface Weekly_planDao{
    @Insert(entity = Weekly_plan::class)
    fun insertNewWeekly_plan(type_id: Int, duration: Int )

    @Delete
    public fun deleteWeekly_planByTypeId(type_id: Int)
}