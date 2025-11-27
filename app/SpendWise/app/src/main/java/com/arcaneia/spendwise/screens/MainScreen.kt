package com.arcaneia.spendwise.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arcaneia.spendwise.data.model.MovViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arcaneia.spendwise.ui.theme.*

/**
 * Pantalla principal de la aplicación, encargada de mostrar el balance del mes
 * actual junto con accesos directos para registrar nuevos gastos e ingresos.
 *
 * Esta pantalla:
 * - Observa en tiempo real el balance mensual obtenido desde [MovViewModel].
 * - Muestra el nombre de la aplicación como cabecera.
 * - Presenta una tarjeta con el balance total de ingresos menos gastos del mes.
 * - Proporciona botones para navegar a las pantallas de:
 *   - Registro de gastos (`expense_screen`)
 *   - Registro de ingresos (`income_screen`)
 *
 * La pantalla está diseñada para ser el punto central de interacción del usuario,
 * ofreciendo una visión clara del estado financiero actual y acciones rápidas.
 *
 * @param navController Controlador de navegación para redirigir a otras pantallas.
 * @param movViewModel ViewModel utilizado para obtener el balance mensual.
 */
@Composable
fun MainScreen(
    navController: NavController,
    movViewModel: MovViewModel
) {

    // Obtiene el balance total de ingresos y gastos del mes actual.
    val balanceMes by movViewModel.balanceMes.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Título principal de la aplicación
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

            // Caja que muestra el balance del mes
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

            // Botón para registrar nuevo gasto
            Button(
                onClick = {
                    navController.navigate("expense_screen")
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp)
                    .width(250.dp)
                    .height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackgroundBoxColorRed,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    "GASTO",
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para registrar nuevo ingreso
            Button(
                onClick = {
                    navController.navigate("income_screen")
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp)
                    .width(250.dp)
                    .height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackgroundBoxColorGreen,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("INGRESO")
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}