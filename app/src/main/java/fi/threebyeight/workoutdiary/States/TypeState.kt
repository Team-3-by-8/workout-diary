package fi.threebyeight.workoutdiary.States

import fi.threebyeight.workoutdiary.Database.type

data class TypeState(
    val types: List<type> = emptyList(),
    val name: String = "",
    val id: Int? = null,
    val isAddingType: Boolean = false
)
