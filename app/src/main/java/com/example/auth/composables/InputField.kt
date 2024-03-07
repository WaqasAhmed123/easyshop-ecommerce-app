package com.example.auth.composables

import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.auth.view.login.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//fun InputField(inputText: String, onValueChange: (String) -> Unit) {
fun InputField(inputText: MutableState<String>, suffixIcon: Boolean = false) {
    val textFieldColors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
//        containerColor = Color.Transparent

    )
    TextField(
        placeholder = {
            Text("abc@gmail.com")
        },

        value = inputText.value,
        onValueChange = {

            inputText.value = it
            println(inputText.value)
        },
        modifier = Modifier
            .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
            .background(Color.Transparent)
            .fillMaxWidth(),
        colors =
        textFieldColors
    )
}