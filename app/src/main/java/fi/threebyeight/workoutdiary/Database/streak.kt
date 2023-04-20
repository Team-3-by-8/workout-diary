package fi.threebyeight.workoutdiary.Database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity
data class streak(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val streak: Int,
    val freezes: Int,
    val freeze_refill_in: Int
)

@Dao
public interface streakDao {
    @Update(entity = streak::class)
    suspend fun updateStreak(streak: streak)

    @Query("SELECT * FROM Streak")
    fun getStreak(): Flow<List<streak>>


}
