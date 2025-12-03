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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arcaneia.spendwise.components.EditarEliminar
import com.arcaneia.spendwise.components.RecurrenceSpinner
import com.arcaneia.spendwise.components.TypeMovSpinner
import com.arcaneia.spendwise.data.entity.MovRecur
import com.arcaneia.spendwise.data.model.MovRecurViewModel
import com.arcaneia.spendwise.ui.theme.*
import com.arcaneia.spendwise.utils.calculateNextDate
import com.arcaneia.spendwise.utils.uiFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.Instant
import java.util.Locale
import com.arcaneia.spendwise.R

/**
 * Pantalla que muestra la lista de movimientos recurrentes configurados por el usuario.
 *
 * Esta pantalla:
 * - Observa el flujo de movimientos recurrentes mediante [MovRecurViewModel].
 * - Muestra cada movimiento en una lista desplazable.
 * - Permite editar o eliminar un movimiento mediante un diálogo contextual.
 * - Permite crear nuevos movimientos recurrentes mediante un botón inferior.
 *
 * Navega hacia:
 * - `newMovRecur_screen` → pantalla de creación de movimientos recurrentes.
 *
 * @param navController Controlador de navegación.
 * @param movRecurViewModel ViewModel para gestionar los movimientos recurrentes.
 */
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
            text = stringResource(id = R.string.recur_movs),
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
            Text(text = stringResource(id = R.string.new_title))
        }
    }
}

/**
 * Lista de movimientos recurrentes con soporte para:
 * - Mostrar lista o mensaje vacío.
 * - Abrir menú para editar/eliminar.
 * - Abrir diálogo de edición.
 *
 * @param movsRecur Lista de movimientos recurrentes.
 * @param modifier Modificador opcional.
 * @param viewModel ViewModel encargado de las operaciones sobre la lista.
 */
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
                text = stringResource(id = R.string.empty_recur_movs),
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
            onEdit = {
                showOptions = false
                showEditDialog = true
            },
            onDelete = {
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

/**
 * Tarjeta que muestra la información principal de un movimiento recurrente.
 *
 * Muestra:
 * - Nombre
 * - Fecha de inicio
 * - Fecha de renovación
 * - Periodo (semanal/mensual/anual)
 * - Importe con color según tipo
 *
 * @param mov Objeto del movimiento recurrente.
 * @param onClick Acción al pulsar el elemento.
 */
@SuppressLint("DefaultLocale")
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
            modifier = Modifier.padding(12.dp),
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
                    text = stringResource(id = R.string.start) + ": " + uiFormat(mov.data_ini),
                    color = Color.Gray,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = stringResource(id = R.string.renew) + ": " + uiFormat(mov.data_rnv),
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
                        .background(Color(0xA9FFD900), RoundedCornerShape(50.dp))
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
                val colorCantidad =
                    if (mov.tipo?.name == "INGRESO") BackgroundBoxColorGreen else BackgroundBoxColorRed

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

/**
 * Diálogo completo para editar un movimiento recurrente.
 *
 * Permite modificar:
 * - Nombre
 * - Importe
 * - Fecha de inicio (DatePicker)
 * - Periodicidad (mediante [RecurrenceSpinner])
 * - Tipo de movimiento (mediante [TypeMovSpinner])
 *
 * La fecha de renovación se actualiza usando [calculateNextDate].
 *
 * @param mov Movimiento recurrente a editar.
 * @param onGuardar Acción que recibe el movimiento actualizado.
 * @param onDismiss Acción al cerrar el diálogo sin guardar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarMovDialog(
    mov: MovRecur,
    onGuardar: (MovRecur) -> Unit,
    onDismiss: () -> Unit
) {
    var nombre by remember { mutableStateOf(mov.nombre) }
    var importe by remember { mutableStateOf(mov.importe.toString()) }
    var fecha by remember { mutableStateOf(mov.data_ini) }
    var periodicidade by remember { mutableStateOf(mov.periodicidade) }
    var tipo by remember { mutableStateOf(mov.tipo) }
    var mostrarPicker by remember { mutableStateOf(false) }

    if (mostrarPicker) {
        val initialMillis = LocalDate.parse(fecha)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        val pickerState = rememberDatePickerState(initialSelectedDateMillis = initialMillis)

        DatePickerDialog(
            onDismissRequest = { mostrarPicker = false },
            confirmButton = {
                TextButton(onClick = {
                    pickerState.selectedDateMillis?.let { millis ->
                        fecha = Instant.ofEpochMilli(millis)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                            .toString()
                    }
                    mostrarPicker = false
                }) { Text(stringResource(id = R.string.accept)) }
            },
            dismissButton = {
                TextButton(onClick = { mostrarPicker = false }) { Text(stringResource(id = R.string.cancel)) }
            }
        ) {
            DatePicker(state = pickerState)
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(id = R.string.edit) + ": " + mov.nombre) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text(stringResource(id = R.string.name)) },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = importe,
                    onValueChange = { importe = it },
                    label = { Text(stringResource(id = R.string.value)+ " (€)") },
                    keyboardOptions = KeyboardOptions(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = uiFormat(fecha),
                    onValueChange = {},
                    label = { Text(stringResource(id = R.string.start_date)) },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { mostrarPicker = true }) {
                            Icon(Icons.Default.CalendarMonth, contentDescription = stringResource(id = R.string.choose_date))
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                RecurrenceSpinner(
                    selectedRecurrence = periodicidade,
                    onRecurrenceSelected = { periodicidade = it }
                )

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
                        onGuardar(
                            mov.copy(
                                nombre = nombre,
                                importe = importe.toDoubleOrNull() ?: mov.importe,
                                data_ini = fecha,
                                data_rnv = calculateNextDate(fecha, periodicidade!!),
                                periodicidade = periodicidade,
                                tipo = tipo
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = BackgroundBoxColorGreen),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.width(120.dp)
                ) {
                    Text(stringResource(id = R.string.save), color = Color.Black)
                }

                Spacer(modifier = Modifier.width(20.dp))

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = BackgroundBoxColorRed),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.width(120.dp)
                ) {
                    Text(stringResource(id = R.string.cancel), color = Color.White)
                }
            }
        },
        dismissButton = {}
    )
}