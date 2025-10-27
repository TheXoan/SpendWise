package com.arcaneia.spendwise.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.model.MovViewModel
import com.arcaneia.spendwise.ui.theme.SubtitleColor
import com.arcaneia.spendwise.ui.theme.SubtitleTextStyle
import com.arcaneia.spendwise.ui.theme.TitleTextStyle
import com.arcaneia.spendwise.ui.theme.TitleTopBar
import com.arcaneia.spendwise.ui.theme.TitleTopBarColor
import com.arcaneia.spendwise.utils.ComboBoxGeneric

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    navController: NavController,
    movViewModel: MovViewModel
) {
    val years by movViewModel.yearsAvailable.collectAsState()
    val months by movViewModel.monthsAvailable.collectAsState()
    val selectedYear by movViewModel.selectedYearFlow.collectAsState()
    val selectedMonth by movViewModel.selectedMonthFlow.collectAsState()
    val movimientos by movViewModel.movements.collectAsState()


    Scaffold(
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
        ) {
            Text(
                text = "Resumen financiero",
                color = Color.White,
                style = TitleTextStyle,
                fontSize = 30.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(30.dp))
            // 🔹 Filtros Año / Mes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 12.dp,
                    alignment = Alignment.CenterHorizontally
                )

            ) {
                ComboBoxGeneric(
                    label = "Año",
                    options = years,
                    selected = selectedYear,
                    onSelected = { movViewModel.onYearSelected(it) }
                )
                ComboBoxGeneric(
                    label = "Mes",
                    options = months,
                    selected = selectedMonth,
                    onSelected = { movViewModel.onMonthSelected(it) },
                    enabled = selectedYear != null
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Lista de movimientos filtrados
            HistoryList(
                transacciones = movimientos,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun HistoryList(
    transacciones: List<Mov>,
    modifier: Modifier = Modifier
) {
    if (transacciones.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No hay movimientos en este periodo",
                color = Color.Gray
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(transacciones) { mov ->
                TransaccionItem(mov)
            }
        }
    }
}

@Composable
fun TransaccionItem(mov: Mov) {
    val esIngreso = mov.tipo == "ingreso"
    val colorCantidad = if (esIngreso) Color(0xFF4CAF50) else Color(0xFFFF5252)

    Surface(
        color = Color(0xFF1A1A24),
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = mov.descricion ?: "Movimiento",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = mov.data_mov ?: "Sin categoría",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
            Text(
                text = mov.categoria_id.toString(),
                color = colorCantidad,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = String.format("%.2f€", mov.importe),
                color = colorCantidad,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LogDefaultPreview(){
    val transacciones: List<Mov>
    //TransaccionItem(mov = transacciones)
}