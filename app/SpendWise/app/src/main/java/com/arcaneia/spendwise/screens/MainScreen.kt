package com.arcaneia.spendwise.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.arcaneia.spendwise.data.model.MovViewModel
import com.arcaneia.spendwise.navigation.AppScreens
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arcaneia.spendwise.data.database.AppDatabase
import com.arcaneia.spendwise.data.entity.Categoria
import com.arcaneia.spendwise.data.entity.Mov
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 💰 Balance
        Text(
            text = "💰 Balance del mes: %.2f €".format(balanceMes),
            style = MaterialTheme.typography.headlineSmall
        )

        // 🟢 Insertar categoría
        Button(onClick = {
            scope.launch(Dispatchers.IO) {
                val categoria = Categoria(
                    id = 1,
                    nombre = "ocio",
                    tipo = "gastos personales"
                )
                db.categoriaDao().insert(categoria)
            }
        }) {
            Text("INSERTAR CATEGORIA")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔴 Insertar gasto
        Button(onClick = {
            scope.launch(Dispatchers.IO) {
                val mov = Mov(
                    tipo = "gasto",
                    importe = 300.25,
                    data_mov = System.currentTimeMillis(),
                    descricion = "Compra de prueba desde Compose",
                    categoria_id = 1,
                    mov_recur_id = null
                )
                db.movDao().insert(mov)
            }
        }) {
            Text("GASTO")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🟢 Insertar ingreso
        Button(onClick = {
            scope.launch(Dispatchers.IO) {
                val mov = Mov(
                    tipo = "ingreso",
                    importe = 510.45,
                    data_mov = System.currentTimeMillis(),
                    descricion = "TEST",
                    categoria_id = 1,
                    mov_recur_id = null
                )
                db.movDao().insert(mov)
            }
        }) {
            Text("INGRESO")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}