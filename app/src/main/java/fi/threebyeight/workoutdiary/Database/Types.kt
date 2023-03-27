package fi.threebyeight.workoutdiary.Database

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Types(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    //not sure if it sets to true
    @ColumnInfo(defaultValue = "1")
    val isListed: Boolean
)
