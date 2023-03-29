package fi.threebyeight.workoutdiary.Database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class workout_diary_Repository(
    private val streakDao: streakDao,
    private val activitiesDao: activitiesDao
) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val Streak_data: Flow<List<Streak>> = streakDao.getStreak()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertStreak(streak: Streak) {
        streakDao.insertStreak(streak)
    }

}