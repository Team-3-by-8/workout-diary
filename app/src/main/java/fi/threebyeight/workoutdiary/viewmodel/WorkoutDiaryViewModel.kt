package fi.threebyeight.workoutdiary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.threebyeight.workoutdiary.Database.*
import fi.threebyeight.workoutdiary.Events.ActivityEvent
import fi.threebyeight.workoutdiary.Events.TypeEvent
import fi.threebyeight.workoutdiary.States.ActivityState
import fi.threebyeight.workoutdiary.States.TypeState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.sql.Date

class WorkoutDiaryViewModel(private val repository: database_Repository) : ViewModel() {
    private val _TypeState = MutableStateFlow(TypeState())
    private val _types =
        repository.types.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _activities =
        repository.activities.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _activityState = MutableStateFlow(ActivityState())
    val activityState = combine(_activityState, _activities) { activityState, activities ->
        activityState.copy(
            activities = activities,
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ActivityState()
        )
    val typeState = combine(_TypeState, _types) { typeState, types ->
        typeState.copy(
            types = types,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TypeState()
    )

    fun onActivityEvent(event: ActivityEvent) {
        when (event) {
            is ActivityEvent.SaveActivity -> {
                onTypeEvent(TypeEvent.SaveType)
                val date = activityState.value.date
                val type_id = repository.getTypeByName(typeState.value.name).id!!
                val duration = activityState.value.duration
                val max_HR = activityState.value.max_HR
                val min_HR = activityState.value.min_HR
                val average_HR = activityState.value.average_HR
                val activity = activities(
                    date = date,
                    type_id = type_id,
                    duration = duration,
                    max_HR = max_HR,
                    min_HR = min_HR,
                    average_HR = average_HR,
                )
                if (date == Date(0, 0, 0) || type_id == 0 || duration == 0) {
                    return
                }
                viewModelScope.launch {
                    repository.insertActivities(activity)
                }
            }

            ActivityEvent.HideDialog -> {
                _activityState.update {
                    it.copy(
                        showDialog = false
                    )
                }
            }

            ActivityEvent.ShowDialog -> {
                _activityState.update {
                    it.copy(
                        showDialog = true
                    )
                }
            }

            ActivityEvent.deleteActivity -> TODO()
            is ActivityEvent.setAverage_HR -> {
                _activityState.update {
                    it.copy(
                        average_HR = event.average_HR
                    )
                }
            }

            is ActivityEvent.setDate -> {
                _activityState.update {
                    it.copy(
                        date = event.date
                    )
                }
            }

            is ActivityEvent.setDuration -> {
                _activityState.update {
                    it.copy(
                        duration = event.duration
                    )
                }
            }

            is ActivityEvent.setMax_HR -> {
                _activityState.update {
                    it.copy(
                        max_HR = event.max_HR
                    )
                }
            }

            is ActivityEvent.setMin_HR -> {
                _activityState.update {
                    it.copy(
                        min_HR = event.min_HR
                    )
                }
            }

            is ActivityEvent.setType_id -> {
                _activityState.update {
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
                val name = typeState.value.name
                if (name.isBlank()) {
                    return
                }
                val type = type(
                    name = name
                )
                viewModelScope.launch {
                    repository.insertType(type)
                }
            }

            TypeEvent.ShowDialog -> {
                _TypeState.update {
                    it.copy(
                        showDialog = true
                    )
                }
            }

            TypeEvent.HideDialog -> {
                _TypeState.update {
                    it.copy(
                        showDialog = false
                    )
                }
            }

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


}