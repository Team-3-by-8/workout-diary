package fi.threebyeight.workoutdiary.Database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Types::class, Weekly_plan::class])
abstract class workout_diary_db : RoomDatabase{
    abstract fun get
}