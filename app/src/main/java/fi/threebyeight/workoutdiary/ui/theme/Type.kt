package fi.threebyeight.workoutdiary.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import fi.threebyeight.workoutdiary.R

val MavenPro = FontFamily(
    Font(R.font.mavenpro_bold),
)

val Righteous = FontFamily(
    Font(R.font.righteous_regular),
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Righteous,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 2.sp
    ),
    body1 = TextStyle(
        fontFamily = Righteous,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = Righteous,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)