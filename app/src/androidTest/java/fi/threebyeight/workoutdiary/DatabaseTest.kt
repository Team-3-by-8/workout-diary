package fi.threebyeight.workoutdiary

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import fi.threebyeight.workoutdiary.Database.type
import fi.threebyeight.workoutdiary.Database.typesDao
import fi.threebyeight.workoutdiary.Database.workout_diary_db
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var typesDao: typesDao
    private lateinit var workoutDiaryDb: workout_diary_db

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        workoutDiaryDb = Room.inMemoryDatabaseBuilder(context, workout_diary_db::class.java)
            .allowMainThreadQueries()
            .build()

        typesDao = workoutDiaryDb.getTypesDao()
    }

    @After
    @Throws(IOException::class)
    fun deleteDb() {
        workoutDiaryDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetTypes() = runBlocking {
        val types = type(name = "test name", isListed = true, id = 1)
        typesDao.insertType(types)
        var value = typesDao.getTypes()
        assertEquals(value[0]?.id, 1)
    }
}