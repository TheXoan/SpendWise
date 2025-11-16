package com.arcaneia.spendwise.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arcaneia.spendwise.components.EditarEliminar
import com.arcaneia.spendwise.components.RecurrenceSpinner
import com.arcaneia.spendwise.components.TypeMovSpinner
import com.arcaneia.spendwise.data.entity.MovRecur
import com.arcaneia.spendwise.data.model.MovRecurViewModel
import com.arcaneia.spendwise.data.model.Recurrence
import com.arcaneia.spendwise.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MovRecurHistoryScreen(
    navController: NavController,
    movRecurViewModel: MovRecurViewModel
) {
    val movsRecur by movRecurViewModel.movRecurList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Movimientos recurrentes",
            style = TitleTextLittle,
            color = Color.White,
            modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        MovRecurList(
            movsRecur = movsRecur,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            viewModel = movRecurViewModel
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { navController.navigate("newMovRecur_screen") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 55.dp)
                .width(250.dp)
                .height(70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundBoxColorGreen,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Nuevo")
        }
    }
}

@Composable
fun MovRecurList(
    movsRecur: List<MovRecur>,
    modifier: Modifier = Modifier,
    viewModel: MovRecurViewModel
) {
    var selectedMov by remember { mutableStateOf<MovRecur?>(null) }
    var showOptions by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }

    if (movsRecur.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No hay movimientos recurrentes configurados",
                color = Color.Gray
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movsRecur) { mov ->
                MovRecurItem(mov) {
                    selectedMov = mov
                    showOptions = true
                }
            }
        }
    }

    if (showOptions && selectedMov != null) {
        EditarEliminar(

            title = selectedMov!!.nombre,
            onEditar = {
                showOptions = false
                showEditDialog = true
            },
            onEliminar = {
                viewModel.delete(selectedMov!!)
                showOptions = false
            },
            onDismiss = { showOptions = false }
        )
    }

    if (showEditDialog && selectedMov != null) {
        EditarMovDialog(
            mov = selectedMov!!,
            onGuardar = {
                viewModel.insert(it)
                showEditDialog = false
            },
            onDismiss = { showEditDialog = false }
        )
    }
}

@SuppressLint("SimpleDateFormat", "DefaultLocale")
@Composable
fun MovRecurItem(mov: MovRecur, onClick: () -> Unit) {
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
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = mov.nombre,
                    color = Color.White,
                    style = TextBoxBold,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "Inicio: " + SimpleDateFormat("dd/MM/yyyy").format(Date(mov.data_ini)),
                    color = Color.Gray,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "Renovación: " + SimpleDateFormat("dd/MM/yyyy").format(Date(mov.data_rnv)),
                    color = Color.Gray,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = mov.periodicidade?.description
                        ?.replaceFirstChar { it.titlecase(Locale.getDefault()) } ?: "",
                    color = Color.Black,
                    style = TextBoxBold,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .background(
                            color = Color(0xA9FFD900),
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                val importeTexto = String.format("%.2f€", mov.importe)
                val fontSize = when {
                    importeTexto.length > 15 -> 11.sp
                    importeTexto.length > 10 -> 12.sp
                    else -> 15.sp
                }

                val colorCantidad = if (mov.tipo?.name == "INGRESO") BackgroundBoxColorGreen else BackgroundBoxColorRed

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarMovDialog(
    mov: MovRecur,
    onGuardar: (MovRecur) -> Unit,
    onDismiss: () -> Unit
) {
    var nombre by remember { mutableStateOf(mov.nombre) }
    var importe by remember { mutableStateOf(mov.importe.toString()) }
    var fecha by remember { mutableStateOf(Date(mov.data_ini)) }
    var periodicidade by remember { mutableStateOf(mov.periodicidade) }
    var tipo by remember { mutableStateOf(mov.tipo) }
    var mostrarPicker by remember { mutableStateOf(false) }

    val formato = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }

    // Date picker para seleccionar la  fecha
    if (mostrarPicker) {
        val pickerState = rememberDatePickerState(initialSelectedDateMillis = mov.data_ini)
        DatePickerDialog(
            onDismissRequest = { mostrarPicker = false },
            confirmButton = {
                TextButton(onClick = {
                    pickerState.selectedDateMillis?.let { fecha = Date(it) }
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
        title = { Text("Editar: ${mov.nombre}") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                // Nombre
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Importe
                OutlinedTextField(
                    value = importe,
                    onValueChange = { importe = it },
                    label = { Text("Importe (€)") },
                    keyboardOptions = KeyboardOptions(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                // Fecha de inicio
                OutlinedTextField(
                    value = formato.format(fecha),
                    onValueChange = {},
                    label = { Text("Fecha de inicio") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { mostrarPicker = true }) {
                            Icon(Icons.Default.CalendarMonth, contentDescription = "Seleccionar fecha")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                // Periodicidad
                RecurrenceSpinner(
                    selectedRecurrence = periodicidade,
                    onRecurrenceSelected = { periodicidade = it }
                )

                // Tipo
                TypeMovSpinner(
                    selectedTypeMov = tipo,
                    onSelectedTypeMov = { tipo = it }
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
                        val nuevaRenovacion = when (periodicidade) {
                            Recurrence.MENSUAL -> fecha.time + 30L * 24 * 60 * 60 * 1000
                            Recurrence.SEMANAL -> fecha.time + 7L * 24 * 60 * 60 * 1000
                            Recurrence.ANUAL -> fecha.time + 365L * 24 * 60 * 60 * 1000
                            else -> mov.data_rnv
                        }

                        onGuardar(
                            mov.copy(
                                nombre = nombre,
                                importe = importe.toDoubleOrNull() ?: mov.importe,
                                data_ini = fecha.time,
                                data_rnv = nuevaRenovacion,
                                periodicidade = periodicidade,
                                tipo = tipo
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