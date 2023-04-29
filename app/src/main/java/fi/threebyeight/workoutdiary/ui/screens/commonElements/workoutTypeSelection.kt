package fi.threebyeight.workoutdiary.ui.screens.commonElements

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fi.threebyeight.workoutdiary.Database.type
import fi.threebyeight.workoutdiary.Events.ActivityEvent
import fi.threebyeight.workoutdiary.Events.TypeEvent
import fi.threebyeight.workoutdiary.R
import fi.threebyeight.workoutdiary.States.ActivityState
import fi.threebyeight.workoutdiary.States.TypeState
import fi.threebyeight.workoutdiary.model.WorkoutType
import fi.threebyeight.workoutdiary.ui.screens.RecordingScreen
import fi.threebyeight.workoutdiary.viewmodel.WorkoutDiaryViewModel
import java.util.*

fun currentDate(): String {
    val calendar = Calendar.getInstance()
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH] + 1
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
    return "$dayOfMonth/$month/$year"
}

@Composable
fun SelectionMain(
    types: List<WorkoutType>,
    RecordNew: Boolean,
    navController: NavController,
    typeState: TypeState,
    activityState: ActivityState,
    viewModel: WorkoutDiaryViewModel
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH] + 1
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]


    var chosenWorkout by remember { mutableStateOf("") }
    var letsRecord by remember { mutableStateOf(false) }
    var dateInput by remember { mutableStateOf(currentDate()) }
    var durationInput by remember { mutableStateOf("30") }
    var measurePulse by remember { mutableStateOf(false) }
    var readyToSave by remember { mutableStateOf(false) }
    val workoutDate = dateInput  // SHOULD BE DATE
    val workoutDuration = durationInput.toIntOrNull() ?: 0

    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            viewModel.onActivityEvent(ActivityEvent.setDate("$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"))
        }, year, month, dayOfMonth
    )
    val maxDaysPast = 14
    datePicker.datePicker.minDate = calendar.timeInMillis - (maxDaysPast * (8.64e+7)).toLong()
    datePicker.datePicker.maxDate = calendar.timeInMillis

    var hideKeyboard by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { hideKeyboard = true },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (chosenWorkout.isEmpty()) {
            ScreenSubTitle("Select Workout")
            WorkoutTypeSelection(
                setChosenWorkout = {
                    chosenWorkout = it
                    viewModel.onTypeEvent(TypeEvent.setName(it))
                },
                typeState.types,
                hideKeyboard,
                viewModel,
                typeState
            ) { hideKeyboard = it }
        } else if (letsRecord) {
            RecordingScreen(navController, chosenWorkout, currentDate(), viewModel)
        } else if (!readyToSave) {
            ScreenSubTitle("Select Workout")
            WorkoutChoiceConfirmation(
                typeState.name,
                setChosenWorkout = {
                    chosenWorkout = it
                    viewModel.onTypeEvent(TypeEvent.setName(it))
                },
                RecordNew,
                setReadyToSave = { readyToSave = it },
                measurePulse,
                setMeasurePulse = { measurePulse = it },
                activityState.date!!,
                setDateInput = { viewModel.onActivityEvent(ActivityEvent.setDate(it)) },
                durationInput,
                setDurationInput = {
                    if (it.isNotBlank()) {
                        viewModel.onActivityEvent(
                            ActivityEvent.setDuration(
                                it.toInt()
                            )
                        )
                    }
                },
                datePicker,
                hideKeyboard,
                viewModel,
                activityState,
                setHideKeyboard = { hideKeyboard = it },
                setLetsRecord = { letsRecord = it }
            )
        } else {
            WorkoutRecord(typeState.name, activityState.date!!, activityState.duration)
            Spacer(modifier = Modifier.weight(1f))
            SaveRecordConfirmation(
                "Save the record?",
                leftChoice = {
                    navController.navigate("Workout")
                    viewModel.onActivityEvent(ActivityEvent.SaveActivity)
                },
                rightChoice = { readyToSave = false }
            )
        }
    }
}

@Composable
fun WorkoutTypeSelection(
    setChosenWorkout: (String) -> Unit,
    types: List<type>,
    hideKeyboard: Boolean,
    viewModel: WorkoutDiaryViewModel,
    typeState: TypeState,
    setHideKeyboard: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 70.dp)
            .padding(top = 17.dp, bottom = 0.dp)
            .fillMaxWidth()
    ) {
        AddNewWorkout(
            setChosenWorkout,
            typeState.name,
            onValueChange = {
                viewModel.onTypeEvent(
                    TypeEvent.setName(
                        it.lowercase().replaceFirstChar(Char::titlecase)
                    )
                )
            },
            hideKeyboard = hideKeyboard,
            onFocusClear = { setHideKeyboard(false) },
            viewModel = viewModel
        )
    }
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 70.dp)
            .padding(top = 0.dp)
            .fillMaxWidth()
    ) {
        items(types) { type ->
            SelectionButton(
                title = type.name,
                onClick = { setChosenWorkout(type.name) },
                width = 9000
            )
        }
    }
}

@Composable
fun AddNewWorkout(
    setChosenWorkout: (String) -> Unit,
    workoutNameInput: String,
    onValueChange: (String) -> Unit,
    hideKeyboard: Boolean = false,
    onFocusClear: () -> Unit = {},
    viewModel: WorkoutDiaryViewModel
) {
    val focusManager = LocalFocusManager.current
    val submitButton = @Composable {
        Button(
            onClick = { setChosenWorkout(workoutNameInput) },
            modifier = Modifier
                .padding(end = 7.dp)
                .height(30.dp)
                .aspectRatio(1f),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            shape = RightArrowShape
        ) {}
    }

    TextField(
        value = workoutNameInput,
        onValueChange = onValueChange,
        label = {
            Text(
                text = "Add New...",
                style = MaterialTheme.typography.button,
                color = Black,
                modifier = Modifier
            )
        },
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Left),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        trailingIcon = if (workoutNameInput.length > 2) submitButton else null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .scale(scaleY = 0.9F, scaleX = 1F)
            .height(54.dp)
            .border(width = 2.dp, Black)
    )

    if (hideKeyboard) {
        focusManager.clearFocus()
        onFocusClear()
    }
}

@Composable
fun InputBox(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    interactionSource: MutableInteractionSource,
    hideKeyboard: Boolean = false,
    onFocusClear: () -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    TextField(
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.button,
                color = Black,
                modifier = Modifier
            )
        },
        value = value,
        onValueChange = onValueChange,
        interactionSource = interactionSource,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Right),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            .scale(scaleY = 0.9F, scaleX = 1F)
            .height(54.dp)
            .border(width = 2.dp, Black)
    )

    if (hideKeyboard) {
        focusManager.clearFocus()
        onFocusClear()
    }
}

@Composable
fun WorkoutChoiceConfirmation(
    chosenWorkout: String,
    setChosenWorkout: (String) -> Unit,
    RecordNew: Boolean,
    setReadyToSave: (Boolean) -> Unit,
    measurePulse: Boolean,
    setMeasurePulse: (Boolean) -> Unit,
    dateInput: String,
    setDateInput: (String) -> Unit,
    durationInput: String,
    setDurationInput: (String) -> Unit,
    datePicker: DatePickerDialog,
    hideKeyboard: Boolean,
    viewModel: WorkoutDiaryViewModel,
    activityState: ActivityState,
    setHideKeyboard: (Boolean) -> Unit,
    setLetsRecord: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 70.dp)
            .padding(top = 17.dp)
            .fillMaxWidth()
    ) {
        SelectionButton(
            title = chosenWorkout,
            onClick = { setChosenWorkout("") }
        )
        if (RecordNew) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "measure")
                    Text(text = "pulse")
                }
                Image(
                    painter = painterResource(id = R.drawable.sensor_inactive),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(50.dp)
                        .clickable { setMeasurePulse(!measurePulse) }
                        .background(
                            if (measurePulse) {
                                Color(red = 223, green = 66, blue = 102)
                            } else {
                                Color.LightGray
                            }
                        )
                )
            }
        } else {
            val dateInteraction = remember { MutableInteractionSource() }
            val durationInteraction = remember { MutableInteractionSource() }

            TextField(
                readOnly = true,
                label = {
                    Text(
                        text = "Date",
                        style = MaterialTheme.typography.button,
                        color = Black,
                        modifier = Modifier
                    )
                },
                value = dateInput,
                onValueChange = { setDateInput(it) },
                interactionSource = dateInteraction.also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                datePicker.show()
                            }
                        }
                    }
                },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Right),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .scale(scaleY = 0.9F, scaleX = 1F)
                    .height(54.dp)
                    .border(width = 2.dp, Black)
            )
            InputBox(
                label = "Minutes",
                value = activityState.duration.toString(),
                onValueChange = { setDurationInput(it) },
                interactionSource = durationInteraction.also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                setDurationInput("0")
                            }
                        }
                    }
                },
                hideKeyboard = hideKeyboard,
                onFocusClear = { setHideKeyboard(false) }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row {
            Spacer(modifier = Modifier.weight(1.5f))
            Box(modifier = Modifier.weight(1f)) {
                if (RecordNew) {
                    SelectionButton(
                        title = "Start",
                        onClick = { setLetsRecord(true) }
                    )
                } else {
                    SelectionButton(
                        title = "Save",
                        onClick = { setReadyToSave(true) })
                }
            }
        }
    }
}

val commonModifier = Modifier
    .fillMaxWidth()
    .padding(horizontal = 80.dp)

@Composable
fun WorkoutRecord(workoutType: String, workoutDate: String, workoutDuration: Int) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            workoutType,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(top = 100.dp, bottom = 17.dp)
        )
        Row(
            modifier = commonModifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Date")
            Text(workoutDate)
        }
        Row(
            modifier = commonModifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Duration")
            Text("$workoutDuration min")
        }
    }
}

@Composable
fun SaveRecordConfirmation(
    theQuestion: String,
    leftChoice: () -> Unit,
    leftButtonText: String = "Yes",
    leftButtonColor: Color = MaterialTheme.colors.primary,
    rightChoice: () -> Unit,
    rightButtonColor: Color = Color.LightGray,
    rightButtonText: String = "No",
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            theQuestion,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 14.dp)
        )
        Row(
            modifier = commonModifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SelectionButton(
                leftButtonText,
                leftChoice,
                leftButtonColor,
                width = 100
            )
            Spacer(modifier = Modifier.weight(0.2f))
            SelectionButton(
                rightButtonText,
                rightChoice,
                rightButtonColor,
                width = 100
            )
        }
    }
}