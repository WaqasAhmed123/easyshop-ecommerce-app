package com.example.auth.view.search

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.auth.view.home.HomeViewModel
import textStyle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchViewScreen(navController: NavController) {
    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                    }
                },

                title = {
                    OutlinedTextField(
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )

                        },
                        placeholder = {
                            Text(
                                text = "Search here",
                                style = textStyle(textColor = Color.Gray)["bodySmall"]!!
                            )
                        },


                        value = HomeViewModel.search.value,
                        textStyle = textStyle(textColor = Color.Gray)["bodySmall"]!!,
                        onValueChange = {

                            HomeViewModel.search.value = it
//                        println(inputText.value)
                        },
                        modifier = Modifier
                            .border(
                                BorderStroke(
                                    width = 2.dp, color = MaterialTheme.colorScheme.secondary
                                ), shape = RoundedCornerShape(50)
                            )
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = (MaterialTheme.colorScheme.secondary),
//                        containerColor = Color.Transparent,
//                        backgroundColor = Purple200.copy(alpha = 0.5f),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                    )
//                    Text(
//                        "Centered Top App Bar",
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis
//                    )
                },

//                actions = {
//                    IconButton(onClick = { /* do something */ }) {
//                        Icon(
//                            imageVector = Icons.Filled.Menu,
//                            contentDescription = "Localized description"
//                        )
//                    }
//                },
            )
        }
//        scrollBehavior = scrollBehavior,
    ) {

    }

}