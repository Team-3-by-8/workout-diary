package fi.threebyeight.workoutdiary.model

data class DummyPlan(
    var workout: String,
    var instances: Int,
    var minutes: Int
)

val dummyPlanForTesting = listOf(
    DummyPlan(
        workout = "Jogging",
        instances = 3,
        minutes = 70
    ),
    DummyPlan(
        workout = "Kettlebell",
        instances = 1,
        minutes = 0
    ),
    DummyPlan(
        workout = "Yoga",
        instances = 2,
        minutes = 60
    ),
    DummyPlan(
        workout = "Any",
        instances = 0,
        minutes = 30
    )
)