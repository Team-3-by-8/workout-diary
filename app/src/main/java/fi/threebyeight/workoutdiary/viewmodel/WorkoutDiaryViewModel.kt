package fi.threebyeight.workoutdiary.viewmodel

import android.widget.ListAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fi.threebyeight.workoutdiary.Database.*
import fi.threebyeight.workoutdiary.Events.ActivityEvent
import fi.threebyeight.workoutdiary.States.ActivityState
import fi.threebyeight.workoutdiary.States.TypeState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WorkoutDiaryViewModel(private val repository: database_Repository) :
    ViewModel() {
    private val _TypeState = MutableStateFlow(TypeState())

    private val _activitieState = MutableStateFlow(ActivityState())

    fun onEvent(event: ActivityEvent) {
        when(event){
            ActivityEvent.HideDialog -> TODO()
            is ActivityEvent.SaveActivity -> {
                viewModelScope.launch {
                    repository.insertType(event.type)
                    val activityWithTypeId = event.activity
//                    val name: String = _TypeState.collect(name)
//
//                    activityWithTypeId.type_id = repository.getTypeByName(_TypeState.value.name)
//                    repository.insertActivities()

                }
            }
            ActivityEvent.ShowDialog -> TODO()
            ActivityEvent.deleteActivity -> TODO()
            is ActivityEvent.setAverage_HR -> TODO()
            is ActivityEvent.setDate -> TODO()
            is ActivityEvent.setDuration -> TODO()
            is ActivityEvent.setMax_HR -> TODO()
            is ActivityEvent.setMin_HR -> TODO()
            is ActivityEvent.setType_id -> TODO()
        }
    }

    val activities: Flow<List<activitiesWithTypeNames>> = repository.activities
    var type: List<type> = emptyList<type>()
    val collecting={
        viewModelScope.launch {
            repository.types.collect() {
               type = it
                println(it)
        }
    }
    }

    val typeState: List<type> = emptyList()
    val streak: Flow<List<streak>> = repository.streak
    val weeklyPlan: Flow<List<weekly_planWithTypeNames>> = repository.weekly_planWithTypeNames
    suspend fun insertStreak(streak: streak) = repository.insertStreak(streak)
    suspend fun updateStreak(streak: streak) = repository.updateStreak(streak)
    suspend fun insertWeekly_plan(weeklyPlan: weekly_plan) =
        repository.insertWeekly_plan(weeklyPlan)

    suspend fun deleteWeekly_plan(weeklyPlan: weekly_plan) =
        repository.deleteWeekly_plan(weeklyPlan)

    suspend fun deleteType(type: type) = repository.deleteType(type)
    suspend fun insertType(type: type) = repository.insertType(type)
    suspend fun updateType(type: type) = repository.updateType(type)


}

class WorkoutDiaryViewModelFactory(private val databaseRepository: database_Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutDiaryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WorkoutDiaryViewModel(databaseRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}