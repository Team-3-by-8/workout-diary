package fi.threebyeight.workoutdiary.Database

import android.content.Context
import androidx.room.*

@Database(
    version = 1,
    entities = [types::class, weekly_plan::class, activities::class, streak::class],
    exportSchema = false
)
@TypeConverters(Converters::class)
public abstract class workout_diary_db : RoomDatabase() {

    abstract fun getTypesDao(): typesDao
    abstract fun getWeekly_planDao(): weekly_planDao

    abstract fun getActivitiesDao(): activitiesDao

    abstract fun getStreakDao(): streakDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: workout_diary_db? = null

        fun getDatabase(context: Context): workout_diary_db {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    workout_diary_db::class.java,
                    "workout_diary_db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}