package com.arcaneia.spendwise.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arcaneia.spendwise.data.model.MovViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arcaneia.spendwise.data.database.AppDatabase
import com.arcaneia.spendwise.data.entity.*
import com.arcaneia.spendwise.ui.theme.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavController,
    movViewModel: MovViewModel
) {
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val scope = rememberCoroutineScope()

    // Obter balance total ingresos e gastos
    val balanceMes by movViewModel.balanceMes.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título principal APP
        Text(
            text = "SpendWise",
            style = TitleTextStyle,
            fontSize = 50.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Balance Ingresos/Gastos",
                style = SubtitleTextStyle,
                color = SubtitleColor
            )
            Spacer(modifier = Modifier.height(50.dp))
            Box(
                modifier = Modifier
                    .width(250.dp)
                    .height(150.dp)
                    .padding(bottom = 40.dp)
                    .background(
                        color = BackgroundBoxColorOne,
                        shape = RoundedCornerShape(50.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "%.2f €".format(balanceMes),
                    style = SubtitleTextStyle,
                    color = Color.Black,
                    fontSize = 35.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Insertar gasto
            Button(
                onClick = {
                    navController.navigate("expense_screen")
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp)
                    .width(250.dp).height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackgroundBoxColorRed,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    "GASTO",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            // 🟢 Insertar ingreso
            Button(
                onClick = {
                    navController.navigate("income_screen")
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp)
                    .width(250.dp).height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackgroundBoxColorGreen,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    "INGRESO"
                )
            }
        }
    }
}