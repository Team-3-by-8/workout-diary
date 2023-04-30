package fi.threebyeight.workoutdiary.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import fi.threebyeight.workoutdiary.Database.database_Repository
import fi.threebyeight.workoutdiary.Database.workout_diary_db
import fi.threebyeight.workoutdiary.R
import fi.threebyeight.workoutdiary.States.ActivityState
import fi.threebyeight.workoutdiary.States.TypeState
import fi.threebyeight.workoutdiary.ui.theme.WorkoutDiaryTheme
import fi.threebyeight.workoutdiary.viewmodel.WorkoutDiaryViewModel

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            workout_diary_db::class.java,
            "workout_diary.db"
        ).createFromAsset("Database/workout_diary_db.db").build()
    }

    private val viewModel by viewModels<WorkoutDiaryViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return WorkoutDiaryViewModel(
                        database_Repository(
                            db.streakDao,
                            db.activitiesDao,
                            db.typesDao,
                            db.planDao
                        )
                    ) as T
                }
            }
        }
    )

    @SuppressLint("StateFlowValueCalledInComposition")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkoutDiaryTheme {
                val typeState by viewModel.typeState.collectAsState()
                val activityState by viewModel.activityState.collectAsState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BasicLayout(typeState, activityState, viewModel)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BasicLayout(
    typeState: TypeState,
    activityState: ActivityState,
    viewModel: WorkoutDiaryViewModel
) {
    val navController = rememberNavController()
    val items = listOf(
        TabItem(ImageVector.vectorResource(id = R.drawable.workout), "Workout"),
        TabItem(ImageVector.vectorResource(id = R.drawable.journal), "Journal"),
        TabItem(ImageVector.vectorResource(id = R.drawable.plan), "Plan"),
        TabItem(ImageVector.vectorResource(id = R.drawable.about), "About")
    )
    var showBottomBar by remember { mutableStateOf(true) }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primaryVariant
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.titleTopAppBar),
                        style = MaterialTheme.typography.h1
                    )
                }
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                AppNavController(
                    navController = navController,
                    setShowBottomBar = { showBottomBar = it },
                    typeState,
                    activityState,
                    viewModel
                )
            }
        },
        bottomBar = {
            if (showBottomBar) {
                MainNavigation(items, navController)
            } else {
                DisabledMainNavigation()
            }
        }
    )
}
