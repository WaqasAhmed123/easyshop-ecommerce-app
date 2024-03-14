package com.example.auth.composables

import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.border
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.auth.view.login.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//fun InputField(inputText: String, onValueChange: (String) -> Unit) {
fun InputField(inputText: MutableState<String>, showTrailingIcon: Boolean = false) {
    var passwordVisibility by remember { mutableStateOf(false) }

    val textFieldColors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
//        containerColor = Color.Transparent

    )
    TextField(
//        isError = true,
        trailingIcon = {
            if (showTrailingIcon) {
                val image =
                    if (passwordVisibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff

                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(imageVector = image, contentDescription = null)
                }

            } else {
                null
            }


        },
        visualTransformation =
        if (showTrailingIcon) {

            if (passwordVisibility) VisualTransformation.None    else PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
//        visualTransformation =  PasswordVisualTransformation(),
//        visualTransformation =  VisualTransformation.None,


        placeholder = {

            Text(if (!showTrailingIcon) "abc@gmail.com" else "********")
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
        colors = textFieldColors
    )
}