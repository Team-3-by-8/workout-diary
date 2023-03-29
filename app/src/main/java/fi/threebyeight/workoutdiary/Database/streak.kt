package fi.threebyeight.workoutdiary.Database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

data class Streak(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val streak: Int,
    val freezes: Int,
    val freeze_refill_in: Int
)

@Dao
public interface streakDao {
    @Insert(entity = Streak::class)
    fun insertStreak(streak: Streak)

    @Query("SELECT * FROM Streak")
    fun getStreak(): Flow<List<Streak>>


}
