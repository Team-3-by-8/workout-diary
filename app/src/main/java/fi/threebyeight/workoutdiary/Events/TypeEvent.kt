package fi.threebyeight.workoutdiary.Events

import fi.threebyeight.workoutdiary.Database.type

// probably won't be used
sealed interface TypeEvent {
    object SaveType: TypeEvent
    data class setName(val name: String) : TypeEvent
    data class setIsListed(val isListed: Boolean) : TypeEvent
    data class setId(val id: Int) : TypeEvent
    object ShowDialog : TypeEvent
    object HideDialog : TypeEvent
    object DeleteType : TypeEvent
}