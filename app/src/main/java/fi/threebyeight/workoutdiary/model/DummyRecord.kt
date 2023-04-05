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
        date = LocalDate.parse("2023-03-24"),
        minutes = 31
    ),
    DummyRecord(
        workout = "Yoga",
        date = LocalDate.parse("2023-03-24"),
        minutes = 20
    ),
    DummyRecord(
        workout = "Jogging",
        date = LocalDate.parse("2023-03-24"),
        minutes = 48
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2023-03-22"),
        minutes = 31
    ),
    DummyRecord(
        workout = "Yoga",
        date = LocalDate.parse("2023-03-20"),
        minutes = 20
    ),
    DummyRecord(
        workout = "Jogging",
        date = LocalDate.parse("2023-03-17"),
        minutes = 48
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2023-03-15"),
        minutes = 28
    ),
    DummyRecord(
        workout = "Yoga",
        date = LocalDate.parse("2023-03-14"),
        minutes = 20
    ),
    DummyRecord(
        workout = "Jogging",
        date = LocalDate.parse("2023-03-14"),
        minutes = 48
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2023-03-10"),
        minutes = 28
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2023-03-05"),
        minutes = 30
    ),
    DummyRecord(
        workout = "Yoga",
        date = LocalDate.parse("2023-03-03"),
        minutes = 26
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2023-03-01"),
        minutes = 31
    ),
    DummyRecord(
        workout = "Yoga",
        date = LocalDate.parse("2023-02-28"),
        minutes = 20
    ),
    DummyRecord(
        workout = "Yoga",
        date = LocalDate.parse("2023-02-28"),
        minutes = 20
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2023-02-25"),
        minutes = 20
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2023-02-20"),
        minutes = 20
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2023-02-20"),
        minutes = 33
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2023-02-17"),
        minutes = 20
    ),
    DummyRecord(
        workout = "Jogging",
        date = LocalDate.parse("2023-02-17"),
        minutes = 20
    ),
    DummyRecord(
        workout = "Yoga",
        date = LocalDate.parse("2023-02-17"),
        minutes = 24
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2023-02-13"),
        minutes = 34
    ),
    DummyRecord(
        workout = "Jogging",
        date = LocalDate.parse("2023-02-10"),
        minutes = 40
    ),
    DummyRecord(
        workout = "Jogging",
        date = LocalDate.parse("2023-02-06"),
        minutes = 50
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
    ),
    DummyRecord(
        workout = "Wrestling",
        date = LocalDate.parse("2022-12-10"),
        minutes = 58
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2022-12-10"),
        minutes = 45
    ),
    DummyRecord(
        workout = "Jogging",
        date = LocalDate.parse("2022-12-08"),
        minutes = 110
    ),
    DummyRecord(
        workout = "Wrestling",
        date = LocalDate.parse("2022-12-05"),
        minutes = 58
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2022-12-05"),
        minutes = 36
    ),
    DummyRecord(
        workout = "Jogging",
        date = LocalDate.parse("2022-12-04"),
        minutes = 110
    ),
    DummyRecord(
        workout = "Wrestling",
        date = LocalDate.parse("2022-12-02"),
        minutes = 58
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2022-11-17"),
        minutes = 40
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2022-11-14"),
        minutes = 41
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2022-11-12"),
        minutes = 20
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2022-11-11"),
        minutes = 34
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2022-11-08"),
        minutes = 43
    ),
    DummyRecord(
        workout = "Kettlebell",
        date = LocalDate.parse("2022-11-05"),
        minutes = 40
    ),
    DummyRecord(
        workout = "Wrestling",
        date = LocalDate.parse("2022-11-03"),
        minutes = 26
    ),
    DummyRecord(
        workout = "Yoga",
        date = LocalDate.parse("2022-11-01"),
        minutes = 16
    )

)