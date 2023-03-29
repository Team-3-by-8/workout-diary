package fi.threebyeight.workoutdiary.Database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class database_Repository(
    private val streakDao: streakDao,
    private val activitiesDao: activitiesDao,
    private val typesDao: typesDao,
    private val weekly_planDao: weekly_planDao
) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val streak: Flow<List<streak>> = streakDao.getStreak()
    val activities: Flow<List<activities>> = activitiesDao.getActivitiesByDate()
    val activitiesWithTypeName: Flow<List<activitiesWithTypeName>> = activitiesDao.getActivitiesWithTypeNames()
    val types: Flow<List<types>> = typesDao.getTypes()
    val weekly_planWithTypeNames: Flow<List<weekly_planWithTypeNames>> = weekly_planDao.getWeekly_planWithTypeName()


    // We can try using it just using Dao, ignoring the Repository
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertStreak(streak: streak) {
        streakDao.insertStreak(streak)
    }

}