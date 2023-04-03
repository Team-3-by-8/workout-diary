package fi.threebyeight.workoutdiary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.threebyeight.workoutdiary.Database.*
import java.sql.Types

class WorkoutDiaryViewModel(private val repository: database_Repository) :
    ViewModel() {
        val activities: List<activitiesWithTypeNames> =  repository.activities
        val types: List<types> = repository.types
        val streak: List<streak> = repository.streak
        val weeklyPlan: List<weekly_planWithTypeNames> = repository.weekly_planWithTypeNames
        suspend fun insertStreak(streak: streak) = repository.insertStreak(streak)
        suspend fun updateStreak(streak: streak) = repository.updateStreak(streak)
        suspend fun insertWeekly_plan(weeklyPlan: weekly_plan)= repository.insertWeekly_plan(weeklyPlan)
        suspend fun deleteWeekly_plan(weeklyPlan: weekly_plan) = repository.deleteWeekly_plan(weeklyPlan)
        suspend fun deleteType(types: types) = repository.deleteType(types)
        suspend fun insertType(types: types) = repository.insertType(types)
        suspend fun updateType(types: types) = repository.updateType(types)



}

class WorkoutDiaryViewModelFactory(private val databasedatabase_Repository: database_Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutDiaryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkoutDiaryViewModel(databasedatabase_Repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}