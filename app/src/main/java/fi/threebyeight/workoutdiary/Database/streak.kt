package fi.threebyeight.workoutdiary.Database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

data class streak(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val streak: Int,
    val freezes: Int,
    val freeze_refill_in: Int
)

@Dao
public interface streakDao {
    @Insert(entity = streak::class)
    suspend fun insertStreak(streak: streak)

    @Query("SELECT * FROM Streak")
    fun getStreak(): Flow<List<streak>>


}
