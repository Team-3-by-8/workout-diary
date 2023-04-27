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
    val streak: Flow<List<streak>> = streakDao.getStreak()
    val activities: Flow<List<activitiesWithTypeNames>> = activitiesDao.getActivities()

    val types: Flow<List<type>> = typesDao.getType()
    val weekly_planWithTypeNames: Flow<List<weekly_planWithTypeNames>> =
        weekly_planDao.getWeekly_planWithTypeName()

    fun getTypeByName(name: String): List<type>{
        return typesDao.getTypeByName(name)
    }


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

    suspend fun deleteType(type: type) {
        typesDao.deleteType(type)
    }

    suspend fun insertType(type: type) {
        typesDao.insertType(type)
    }

    suspend fun updateType(type: type) {
        typesDao.updateType(type)
    }



}