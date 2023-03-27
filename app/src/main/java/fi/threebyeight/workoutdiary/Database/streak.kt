package fi.threebyeight.workoutdiary.Database

import androidx.room.Dao
import androidx.room.PrimaryKey
import androidx.room.Update

data class streak(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val streak: Int,
    val freezes: Int,
    val freeze_refill_in: Int
)
//ironically
data class streakStreak (
    val id: Long,
    val streak: Int,
    val freezes: Int,
    val freeze_refill_in: Int
)
@Dao
public interface streakDao {
    @Update(entity = streak::class)
    fun updateStreak(varargs streak: Streak)
}
