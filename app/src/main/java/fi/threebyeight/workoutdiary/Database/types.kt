package fi.threebyeight.workoutdiary.Database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

data class types(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    //not sure if it sets to true
    @ColumnInfo(defaultValue = "1")
    val isListed: Boolean
)

@Dao
public interface typesDao {
    @Insert(entity = types::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTypes(type: types)

    @Delete
    suspend fun deleteType(type: types)

    @Query("SELECT * FROM Types ORDER BY id ASC")
    fun getTypes(): Flow<List<types>>


}
