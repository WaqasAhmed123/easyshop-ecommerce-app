import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun textStyle(textColor: Color = Color(0xFF000000)): Map<String, TextStyle> {
    return mapOf(
        "titleLarge" to TextStyle(
            fontSize = 16.sp,
            color = textColor,
            fontWeight = FontWeight.W500
        ),
        "titleMedium" to TextStyle(

            fontSize = 14.sp,
            color = textColor,
            fontWeight = FontWeight.W500        ),
        "titleSmall" to TextStyle(fontSize = 14.sp, color = textColor),
        "bodySmall" to TextStyle(

            fontSize = 12.sp,
            color = textColor,
            fontWeight = FontWeight.W500
        )
    )
}
