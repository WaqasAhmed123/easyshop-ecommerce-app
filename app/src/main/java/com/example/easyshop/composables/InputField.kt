package com.example.easyshop.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
//fun InputField(inputText: String, onValueChange: (String) -> Unit) {
fun InputField(
    inputText: MutableState<String>,
    showTrailingIcon: Boolean = false,
//    onNextFieldRequested: (() -> Unit)? = null,
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = BringIntoViewRequester()
    val scope = rememberCoroutineScope()


    val textFieldColors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
//        containerColor = Color.Transparent

    )
    TextField(
//        keyboardActions = KeyboardActions(imeAction=ImeAction.Done ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
//            onNextFieldRequested?.invoke()
            focusManager.clearFocus()
            println("Focus requested for next field")
        }),
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
        visualTransformation = if (showTrailingIcon) {

            if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
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
            .background(Color.Transparent, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .onFocusEvent { event ->
                if (event.isFocused) {
                    scope.launch {
                        bringIntoViewRequester.bringIntoView()

                    }

                }
            }
            .bringIntoViewRequester(bringIntoViewRequester),
        colors = textFieldColors)
}