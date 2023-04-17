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
    val streak: List<streak> = streakDao.getStreak()
    val activities: List<activitiesWithTypeNames> = activitiesDao.getActivities()

    val types: List<types> = typesDao.getTypes()
    val weekly_planWithTypeNames: List<weekly_planWithTypeNames> =
        weekly_planDao.getWeekly_planWithTypeName()


    // We can try using it just using Dao, ignoring the Repository
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertStreak(streak: streak) {
        streakDao.updateStreak(streak)
    }

    suspend fun insertActivities(activities: activities) {
        activitiesDao.insertActivity(activities)
    }

    suspend fun updateStreak(streak: streak) {
        streakDao.updateStreak(streak)
    }

    suspend fun insertWeekly_plan(weekly_plan: weekly_plan) {
        weekly_planDao.insertNewWeekly_plan(weekly_plan)
    }

    suspend fun deleteWeekly_plan(weekly_plan: weekly_plan) {
        weekly_planDao.deleteWeekly_plan(weekly_plan = weekly_plan)
    }

    suspend fun deleteType(types: types) {
        typesDao.deleteType(types)
    }

    suspend fun insertType(types: types) {
        typesDao.insertType(types)
    }

    suspend fun updateType(types: types) {
        typesDao.updateType(types)
    }

}