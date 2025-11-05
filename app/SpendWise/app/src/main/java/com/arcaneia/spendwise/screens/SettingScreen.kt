package com.arcaneia.spendwise.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arcaneia.spendwise.ui.theme.BackgroundBoxHistory
import com.arcaneia.spendwise.ui.theme.TitleTopBar

@Composable
fun SettingScreen(
    navController: NavController,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 30.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Configuración",
            style = TitleTopBar,
            fontSize = 40.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
            // PENDIENTE IMPLEMENTAR
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
                .width(250.dp).height(70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundBoxHistory,
            ),
            shape = RoundedCornerShape(40.dp),
        ) {
            Text(
                "Importar Datos",
                color = Color.White,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
            // PENDIENTE IMPLEMENTAR
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
                .width(250.dp).height(70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundBoxHistory,
            ),
            shape = RoundedCornerShape(40.dp),
        ) {
            Text(
                "Exportar Datos",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}