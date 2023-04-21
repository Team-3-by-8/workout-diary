package fi.threebyeight.workoutdiary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fi.threebyeight.workoutdiary.Database.*
import fi.threebyeight.workoutdiary.Events.ActivityEvent
import fi.threebyeight.workoutdiary.Events.TypeEvent
import fi.threebyeight.workoutdiary.States.ActivityState
import fi.threebyeight.workoutdiary.States.TypeState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WorkoutDiaryViewModel(private val repository: database_Repository) :
    ViewModel() {
    private val _TypeState = MutableStateFlow(TypeState())

    private val _activitieState = MutableStateFlow(ActivityState())

    fun onActivityEvent(event: ActivityEvent) {
        when (event) {
            is ActivityEvent.SaveActivity -> {
                viewModelScope.launch {
                    onTypeEvent(TypeEvent.SaveType(event.type)) //we insert the type
                    event.activity.type_id = repository.getTypeByName(event.type.name).id!!
                    repository.insertActivities(event.activity)
                }
            }
            ActivityEvent.HideDialog -> {
                _activitieState.update {
                    it.copy(
                        showDialog = false
                    )
                }
            }
            ActivityEvent.ShowDialog -> {
                _activitieState.update {
                    it.copy(
                        showDialog = true
                    )
                }
            }
            ActivityEvent.deleteActivity -> TODO()
            is ActivityEvent.setAverage_HR -> {
                _activitieState.update {
                    it.copy(
                        average_HR = event.average_HR
                    )
                }
            }
            is ActivityEvent.setDate -> {
                _activitieState.update {
                    it.copy(
                        date = event.date
                    )
                }
            }
            is ActivityEvent.setDuration -> {
                _activitieState.update {
                    it.copy(
                        duration = event.duration
                    )
                }
            }
            is ActivityEvent.setMax_HR -> {
                _activitieState.update {
                    it.copy(
                        max_HR = event.max_HR
                    )
                }
            }
            is ActivityEvent.setMin_HR -> {
                _activitieState.update {
                    it.copy(
                        min_HR = event.min_HR
                    )
                }
            }
            is ActivityEvent.setType_id -> {
                _activitieState.update {
                    it.copy(
                        type_id = event.type_id
                    )
                }
            }
        }
    }

    fun onTypeEvent(event: TypeEvent) {
        when (event) {
            is TypeEvent.DeleteType -> TODO()
            is TypeEvent.SaveType -> {
                viewModelScope.launch {
                    repository.insertType(event.type)
                }
            }
            TypeEvent.ShowDialog -> {_TypeState.update{
                it.copy(
                    showDialog = true
                )
            }}
            TypeEvent.HideDialog -> {_TypeState.update {
                it.copy(
                    showDialog = false
                )
            }}
            is TypeEvent.setId -> {
                _TypeState.update {
                    it.copy(
                        id = event.id
                    )
                }
            }
            is TypeEvent.setIsListed -> {
                _TypeState.update {
                    it.copy(
                        isListed = event.isListed
                    )
                }
            }
            is TypeEvent.setName -> {
                _TypeState.update {
                    it.copy(
                        name = event.name
                    )
                }
            }
        }
    }

    val activities: Flow<List<activitiesWithTypeNames>> = repository.activities
    var type: List<type> = emptyList<type>()

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