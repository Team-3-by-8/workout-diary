package fi.threebyeight.workoutdiary.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

data class DummyRecord(
    var workout: String,
    var date: LocalDate,
    var minutes: Int
)
@RequiresApi(Build.VERSION_CODES.O)
val dummyData = listOf(
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2023-10-09"),
        minutes = 31
    ),
    DummyRecord(
        workout = "Yoga",
        date = LocalDate.parse("2023-11-09"),
        minutes = 20
    ),
    DummyRecord(
        workout = "Jogging",
        date = LocalDate.parse("2023-01-09"),
        minutes = 48
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2023-01-07"),
        minutes = 28
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2023-01-05"),
        minutes = 30
    ),
    DummyRecord(
        workout = "Yoga",
        date = LocalDate.parse("2023-01-03"),
        minutes = 26
    ),
    DummyRecord(
        workout = "Wrestling",
        date = LocalDate.parse("2022-12-17"),
        minutes = 58
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2022-12-17"),
        minutes = 40
    ),
    DummyRecord(
        workout = "Jogging",
        date = LocalDate.parse("2022-12-12"),
        minutes = 110
    )
)