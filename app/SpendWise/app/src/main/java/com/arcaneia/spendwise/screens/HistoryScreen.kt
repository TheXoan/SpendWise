package com.arcaneia.spendwise.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arcaneia.spendwise.components.EditarEliminar
import com.arcaneia.spendwise.components.RecurrenceSpinner
import com.arcaneia.spendwise.components.TypeMovSpinner
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.entity.MovRecur
import com.arcaneia.spendwise.data.entity.MovWithCategory
import com.arcaneia.spendwise.data.model.CategoriaViewModel
import com.arcaneia.spendwise.data.model.MovViewModel
import com.arcaneia.spendwise.data.model.Recurrence
import com.arcaneia.spendwise.data.model.TypeMov
import com.arcaneia.spendwise.ui.theme.BackgroundBoxCategory
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorGreen
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorRed
import com.arcaneia.spendwise.ui.theme.BackgroundBoxHistory
import com.arcaneia.spendwise.ui.theme.TextBoxBold
import com.arcaneia.spendwise.ui.theme.TitleBox
import com.arcaneia.spendwise.ui.theme.TitleTextLittle
import com.arcaneia.spendwise.ui.theme.TitleTextStyle
import com.arcaneia.spendwise.utils.ComboBoxCategorias
import com.arcaneia.spendwise.utils.ComboBoxHistory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    navController: NavController,
    movViewModel: MovViewModel,
    categoriaViewModel: CategoriaViewModel
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
                style = TitleTextLittle,
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
                modifier = Modifier.fillMaxSize(),
                viewModel = movViewModel,
                categoriaViewModel = categoriaViewModel
            )
        }
    }
}

@Composable
fun HistoryList(
    transacciones: List<MovWithCategory>,
    modifier: Modifier = Modifier,
    viewModel: MovViewModel,
    categoriaViewModel: CategoriaViewModel
) {

    var selectedMov by remember { mutableStateOf<MovWithCategory?>(null) }
    var showOptions by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }

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
                TransaccionItem(mov){
                    selectedMov = mov
                    showOptions = true
                }
                Spacer(modifier = Modifier.height( 5.dp ))
            }
        }
    }
    if (showOptions && selectedMov != null && selectedMov!!.mov.descricion!= null ) {
        EditarEliminar(
            title = selectedMov!!.mov.descricion!!,
            onEditar = {
                showOptions = false
                showEditDialog = true
            },
            onEliminar = {
                viewModel.delete(selectedMov!!.mov)
                showOptions = false
            },
            onDismiss = { showOptions = false }
        )
    }

    if (showEditDialog && selectedMov != null) {
        EditMovDialog(
            mov = selectedMov!!.mov,
            onGuardar = {
                viewModel.update(it)
                showEditDialog = false
            },
            onDismiss = { showEditDialog = false },
            categoriaViewModel = categoriaViewModel
        )
    }
}

@SuppressLint(
    "DefaultLocale"
)
@Composable
fun TransaccionItem(
    movWithCategory: MovWithCategory,
    onClick: () -> Unit,
) {
    val esIngreso = movWithCategory.mov.tipo == TypeMov.INGRESO
    val colorCantidad = if (esIngreso) BackgroundBoxColorGreen else BackgroundBoxColorRed

    Surface(
        color = BackgroundBoxHistory,
        shape = RoundedCornerShape(50.dp),
        tonalElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = movWithCategory.mov.descricion ?: "Movimiento",
                    color = Color.White,
                    style = TextBoxBold,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                        .format(
                            SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                                .parse(movWithCategory.mov.data_mov)!!
                        ),
                    color = Color.Gray,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = movWithCategory.categoriaNome,
                    color = Color.Black,
                    style = TextBoxBold,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .background(color = Color(
                            0xA9FFD900
                        ), shape = RoundedCornerShape(50.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                // Si la cifra es muy alta reduce el tamaño del texto
                val importeTexto = String.format("%.2f€", movWithCategory.mov.importe)
                val fontSize = when {
                    importeTexto.length > 15 -> 11.sp
                    importeTexto.length > 10 -> 12.sp
                    else -> 15.sp
                }
                Text(
                    text = importeTexto,
                    color = colorCantidad,
                    style = TitleBox,
                    fontSize = fontSize,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
        }

    }
}

@SuppressLint(
    "SimpleDateFormat"
)
@Composable
fun EditMovDialog(
    mov: Mov,
    onGuardar: (Mov) -> Unit,
    onDismiss: () -> Unit,
    categoriaViewModel: CategoriaViewModel
){
    var nameMov by remember { mutableStateOf(mov.descricion) }
    var amountMov by remember { mutableStateOf(mov.importe.toString()) }

    val formatoDB = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    var date by remember { mutableStateOf(formatoDB.parse(mov.data_mov) ?: Date()) }

    var tipo by remember { mutableStateOf(mov.tipo) }
    var mostrarPicker by remember { mutableStateOf(false) }
    var categoria by remember { mutableStateOf(mov.categoria_id) }

    val formato = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }

    // Date picker para seleccionar la  fecha
    if (mostrarPicker) {
        val pickerState = rememberDatePickerState(initialSelectedDateMillis = date.time)
        DatePickerDialog(
            onDismissRequest = { mostrarPicker = false },
            confirmButton = {
                TextButton(onClick = {
                    pickerState.selectedDateMillis?.let { date = Date(it) }
                    mostrarPicker = false
                }) { Text("Aceptar") }
            },
            dismissButton = {
                TextButton(onClick = { mostrarPicker = false }) { Text("Cancelar") }
            }
        ) {
            DatePicker(state = pickerState)
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar: ${mov.descricion}") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                // Nombre
                OutlinedTextField(
                    value = nameMov?: "",
                    onValueChange = { nameMov = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Importe
                OutlinedTextField(
                    value = amountMov,
                    onValueChange = { amountMovMod ->
                        // Solo permite números y decimales separados por punto
                        if (amountMovMod.matches(
                                Regex("^\\d*\\.?\\d*\$"))
                        ) { amountMov = amountMovMod }
                    },
                    label = { Text("Importe (€)") },
                    keyboardOptions = KeyboardOptions(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                // Fecha de inicio
                OutlinedTextField(
                    value = formato.format(date),
                    onValueChange = {},
                    label = { Text("Fecha del gasto") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { mostrarPicker = true }) {
                            Icon(Icons.Default.CalendarMonth, contentDescription = "Seleccionar fecha")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                // Tipo
                TypeMovSpinner(
                    selectedTypeMov = tipo,
                    onSelectedTypeMov = { tipo = it }
                )

                ComboBoxCategorias(
                    categoriaViewModel,
                    onCategoriaSeleccionada = { id ->
                        categoria = id
                    },
                    internalShape = RoundedCornerShape(12.dp)
                )

            }
        },
        confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {

                        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)

                        onGuardar(
                            mov.copy(
                                id = mov.id,
                                tipo = tipo,
                                importe = amountMov.toDouble(),
                                data_mov = dateFormat,
                                descricion = nameMov,
                                categoria_id = categoria,
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = BackgroundBoxColorGreen),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.width(120.dp)
                ) {
                    Text("Guardar", color = Color.Black)
                }

                Spacer(modifier = Modifier.width(20.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = BackgroundBoxColorRed),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.width(120.dp)
                ) {
                    Text("Cancelar", color = Color.White)
                }
            }
        },
        dismissButton = {}
    )
}