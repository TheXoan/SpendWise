package com.arcaneia.spendwise.screens

import android.annotation.SuppressLint
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.arcaneia.spendwise.data.entity.MovWithCategory
import com.arcaneia.spendwise.data.model.MovViewModel
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorGreen
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorRed
import com.arcaneia.spendwise.ui.theme.BackgroundBoxHistory
import com.arcaneia.spendwise.ui.theme.TitleBox
import com.arcaneia.spendwise.ui.theme.TitleTextStyle
import com.arcaneia.spendwise.utils.ComboBoxHistory
import java.text.SimpleDateFormat
import java.util.Locale

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
                ComboBoxHistory(
                    label = "Año",
                    options = years,
                    selected = selectedYear,
                    onSelected = { movViewModel.onYearSelected(it) },
                    modifier = Modifier.weight(1f)
                )
                ComboBoxHistory(
                    label = "Mes",
                    options = months,
                    selected = selectedMonth,
                    onSelected = { movViewModel.onMonthSelected(it) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

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
    transacciones: List<MovWithCategory>,
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

@SuppressLint(
    "DefaultLocale"
)
@Composable
fun TransaccionItem(
    movWithCategory: MovWithCategory
) {
    val esIngreso = movWithCategory.mov.tipo == "ingreso"
    val colorCantidad = if (esIngreso) BackgroundBoxColorGreen else BackgroundBoxColorRed

    Surface(
        color = BackgroundBoxHistory,
        shape = RoundedCornerShape(50.dp),
        tonalElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = movWithCategory.mov.descricion ?: "Movimiento",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text =  SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.getDefault())
                        .format(SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                            Locale.getDefault()).parse(movWithCategory.mov.data_mov)!!),
                    color = Color.Gray,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 10.dp)

                )
            }
            Text(
                text = movWithCategory.categoriaNome,
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier.padding(end = 50.dp)
            )
            Text(
                text = String.format("%.2f€", movWithCategory.mov.importe),
                color = colorCantidad,
                style = TitleBox,
                modifier = Modifier.padding(end = 10.dp)
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