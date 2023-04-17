package fi.threebyeight.workoutdiary.Database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity
data class types(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    //not sure if it sets to true
    @ColumnInfo(defaultValue = "1")
    val isListed: Boolean?
)

@Dao
public interface typesDao {
    @Insert(entity = types::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertType(types: types)

    @Update
    suspend fun updateType(types: types)

    @Delete
    suspend fun deleteType(types: types)

    @Query("SELECT * FROM Types ORDER BY id ASC")
    fun getTypes(): List<types>

    //just for test, won't be added to Repository
    @Query("SELECT * FROM types WHERE id = :id")
    fun getTypeById(id: Int): types


}
