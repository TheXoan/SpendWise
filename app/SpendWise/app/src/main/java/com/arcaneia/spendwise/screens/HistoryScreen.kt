package com.arcaneia.spendwise.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arcaneia.spendwise.ui.theme.TitleTopBar
import com.arcaneia.spendwise.ui.theme.TitleTopBarColor
import com.arcaneia.spendwise.utils.comboBoxYearMovs
import com.arcaneia.spendwise.utils.comboBoxMonthYearMovs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Historial financiero",
                    style = TitleTopBar)},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = TitleTopBarColor,
                    titleContentColor = Color.White,
                )
            )
        },
        containerColor = Color(0xFF0D0D14),
    ) { innerPadding ->
        Column {
            Row (
                modifier =
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
            ){
                comboBoxYearMovs()
                comboBoxMonthYearMovs()
            }
            Spacer(modifier = Modifier.height(30.dp))
            HistorialList(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun HistorialList(
    modifier: Modifier
) {
    // IMPLEMENTAR LAZY LIST
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LogDefaultPreview(){
    HistoryScreen(navController = rememberNavController())
}