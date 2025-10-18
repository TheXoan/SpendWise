package com.arcaneia.spendwise.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LogScreen(navController: NavController) {

    Scaffold { LogBodyContent(navController) }
}

@Composable
fun LogBodyContent(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Hi! Son LOG SCREEN!! 📚📚📚")
        Button(onClick = { }) {
            Text("TEST")
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun LogDefaultPreview(){
    LogScreen()
}
*/