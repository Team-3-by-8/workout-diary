package fi.threebyeight.workoutdiary.Database

import android.content.Context
import androidx.room.*

@Database(
    version = 1,
    entities = [type::class, weekly_plan::class, activities::class, streak::class],
    exportSchema = false
)
abstract class workout_diary_db : RoomDatabase() {

    abstract val typesDao: typesDao
    abstract val planDao: weekly_planDao

    abstract val activitiesDao: activitiesDao

    abstract val streakDao: streakDao

}