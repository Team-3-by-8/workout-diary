package fi.threebyeight.workoutdiary.Database

import androidx.room.*
import kotlinx.coroutines.flow.Flow


//set the name of the table and put unique constraint on name, will do some dirty stuff
@Entity(tableName = "Type", indices = arrayOf(Index(value = arrayOf("name"), unique = true)))
data class type(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val isListed: Boolean = true
)

@Dao
public interface typesDao {

    //Here comes the dirty stuff, replace
    @Insert(entity = type::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertType(type: type)

    @Update
    suspend fun updateType(type: type)

    @Delete
    suspend fun deleteType(type: type)

    @Query("SELECT * FROM Type ORDER BY id ASC")
    fun getType(): Flow<List<type>>

    //just for test, won't be added to Repository
    @Query("SELECT * FROM Type WHERE id = :id LIMIT 1")
    fun getTypeById(id: Int): Flow<type>

    @Query("SELECT * FROM Type where name = :name LIMIT 1")
    fun getTypeByName(name: String): type
}
