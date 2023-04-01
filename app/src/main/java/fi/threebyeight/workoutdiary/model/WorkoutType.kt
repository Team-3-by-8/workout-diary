package fi.threebyeight.workoutdiary.model

data class WorkoutType(
    var name: String,
    var defaultMinutes: Int,
)

val dummyWorkoutTypeList = listOf(
    WorkoutType(
        name = "Jogging",
        defaultMinutes = 40
    ),
    WorkoutType(
        name = "Kettlebell",
        defaultMinutes = 30
    ),
    WorkoutType(
        name = "Yoga",
        defaultMinutes = 20
    ),
    WorkoutType(
        name = "Walking",
        defaultMinutes = 40
    ),
    WorkoutType(
        name = "Biking",
        defaultMinutes = 15
    ),
    WorkoutType(
        name = "Skiing",
        defaultMinutes = 30
    ),
    WorkoutType(
        name = "Dancing",
        defaultMinutes = 30
    ),
    WorkoutType(
        name = "Boxing",
        defaultMinutes = 30
    ),
    WorkoutType(
        name = "Acrobatics",
        defaultMinutes = 30
    )
)