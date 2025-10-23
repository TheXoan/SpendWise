package com.arcaneia.spendwise.screens

import android.content.res.Configuration
import android.widget.Space
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
import androidx.compose.ui.tooling.preview.Preview
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

    val balanceMes by movViewModel.balanceMes.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 🏷️ Título arriba
        Text(
            text = "SpendWise",
            style = TitleTextStyle,
            fontSize = 50.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // 📋 Contenido principal centrado
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Balance Ingresos/Gastos",
                style = SubtitleTextStyle,
                color = MaterialTheme.colorScheme.secondary
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
                contentAlignment = Alignment.Center // 👈 centra el texto horizontal y verticalmente
            ) {
                Text(
                    text = "%.2f €".format(balanceMes),
                    style = SubtitleTextStyle,
                    color = Color.Black,
                    fontSize = 35.sp
                )
            }


            Spacer(
                modifier = Modifier.height(
                    14.dp
                )
            )

            // 🟢 Insertar categoría
            Button(
                onClick = {
                    scope.launch(
                        Dispatchers.IO
                    ) {
                        val categoria =
                            Categoria(
                                id = 1,
                                nombre = "ocio",
                                tipo = "gastos personales",
                            )
                        db.categoriaDao().insert(categoria)
                    }

                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally) // 👈 centra el botón en su contenedor
                    .padding(bottom = 10.dp)
                    .width(250.dp).height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackgroundBoxColorOne, // 👈 fondo del botón
                    contentColor = Color.Black              // 👈 color del texto
                ),
                shape = RoundedCornerShape(12.dp),
                ) {
                Text(
                    text = "INSERTAR CATEGORIA",
                )
            }

            Spacer(
                modifier = Modifier.height(
                    16.dp
                )
            )

            // 🔴 Insertar gasto
            Button(
                onClick = {
                    scope.launch(
                        Dispatchers.IO
                    ) {
                        val mov =
                            Mov(
                                tipo = "gasto",
                                importe = 300.25,
                                data_mov = System.currentTimeMillis(),
                                descricion = "Compra de prueba desde Compose",
                                categoria_id = 1,
                                mov_recur_id = null
                            )
                        db.movDao()
                            .insert(
                                mov
                            )
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally) // 👈 centra el botón en su contenedor
                    .padding(bottom = 10.dp)
                    .width(250.dp).height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackgroundBoxColorRed, // 👈 fondo del botón
                    contentColor = Color.Black              // 👈 color del texto
                ),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    "GASTO",
                    color = Color.White
                )
            }

            Spacer(
                modifier = Modifier.height(
                    16.dp
                )
            )

            // 🟢 Insertar ingreso
            Button(
                onClick = {
                    scope.launch(
                        Dispatchers.IO
                    ) {
                        val mov =
                            Mov(
                                tipo = "ingreso",
                                importe = 510.45,
                                data_mov = System.currentTimeMillis(),
                                descricion = "TEST",
                                categoria_id = 1,
                                mov_recur_id = null
                            )
                        db.movDao()
                            .insert(
                                mov
                            )
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally) // 👈 centra el botón en su contenedor
                    .padding(bottom = 10.dp)
                    .width(250.dp).height(70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackgroundBoxColorGreen, // 👈 fondo del botón
                    contentColor = Color.White             // 👈 color del texto
                ),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    "INGRESO"
                )
            }

            Spacer(
                modifier = Modifier.height(
                    16.dp
                )
            )
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun test(){


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 🏷️ Título arriba
        Text(
            text = "SpendWise",
            style = TitleTextStyle,
            fontSize = 45.sp,
            modifier = Modifier.align(Alignment.TopCenter)
            .padding(top = 130.dp)
        )

        // 📋 Contenido centrado
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Balance Ingresos/Gastos",
                style = SubtitleTextStyle
            )

            Spacer(modifier = Modifier.height(25.dp))
            Button(onClick = {}) { Text("INSERTAR CATEGORIA") }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {}) { Text("GASTO") }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {}) { Text("INGRESO") }
        }
    }

}