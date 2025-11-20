package com.arcaneia.spendwise.screens

import android.os.Build
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arcaneia.spendwise.components.RecurrenceSpinner
import com.arcaneia.spendwise.components.TypeMovSpinner
import com.arcaneia.spendwise.data.entity.MovRecur
import com.arcaneia.spendwise.data.model.MovRecurViewModel
import com.arcaneia.spendwise.data.model.Recurrence
import com.arcaneia.spendwise.data.model.TypeMov
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorGreen
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOne
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOneSelected
import com.arcaneia.spendwise.ui.theme.SubtitleColorn2
import com.arcaneia.spendwise.ui.theme.TitleBox
import com.arcaneia.spendwise.ui.theme.TitleTopBar
import com.arcaneia.spendwise.ui.theme.TittleTopBox
import com.arcaneia.spendwise.utils.calculateNextDate
import com.arcaneia.spendwise.utils.formatDateForDB
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun NewMovRecurScreen(
    navController: NavController,
    movRecurViewModel: MovRecurViewModel
){

    val context = LocalContext.current

    var amount by remember {mutableStateOf("")}
    var name by remember {mutableStateOf("")}

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var selectedDate by remember { mutableStateOf(
        LocalDate.now().format(formatter)) }
    var mostrarPicker by remember { mutableStateOf(false) }

    if (mostrarPicker) {

        val initialMillis = LocalDate.parse(selectedDate, formatter)
            .atStartOfDay(
                ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        val pickerState = rememberDatePickerState(initialSelectedDateMillis = initialMillis)

        DatePickerDialog(
            onDismissRequest = { mostrarPicker = false },
            confirmButton = {
                TextButton(onClick = {
                    pickerState.selectedDateMillis?.let { millis ->
                        selectedDate = Instant.ofEpochMilli(millis)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                            .format(formatter) // dd/MM/yyyy
                    }
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

    var selectedRecurrence by remember { mutableStateOf<Recurrence?>(null) }
    var selectedTypeMov by remember { mutableStateOf<TypeMov?>(null) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(
                horizontal = 30.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Nuevo movimiento recurrente",
            style = TitleTopBar,
            fontSize = 30.sp,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height( 30.dp ))

        Text(
            text = "Importe",
            modifier = Modifier.align(Alignment.Start),
            color = SubtitleColorn2,
            style = TittleTopBox
        )
        OutlinedTextField(
            value = amount,
            onValueChange = { amountMod ->
                // Solo permite números y decimales separados por punto
                if (amountMod.matches(
                        Regex("^\\d*\\.?\\d*\$"))
                ) { amount = amountMod }
            },
            placeholder = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "0,0",
                        style = TitleBox,
                        color = Color.Black,
                        fontSize = 30.sp
                    )
                    Text(
                        text = "€",
                        style = TitleBox,
                        color = Color.Black,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(
                            start = 4.dp
                        )
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
            ),
            shape = RoundedCornerShape(
                12.dp
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).height(80.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = BackgroundBoxColorOne,
                focusedContainerColor = BackgroundBoxColorOneSelected,
                cursorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.height( 15.dp ))
        Text(
            text = "Nombre",
            modifier = Modifier.align(Alignment.Start),
            color = SubtitleColorn2,
            style = TittleTopBox
        )
        OutlinedTextField(
            value = name,
            onValueChange = { userInput ->
                name = userInput
            },
            placeholder = { Text("Nombre gasto recurrente", color = Color.Black, style = TitleBox, fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = BackgroundBoxColorOne,
                focusedContainerColor = BackgroundBoxColorOneSelected,
                cursorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp)
        )
        Spacer(modifier = Modifier.height( 15.dp ))
        Text(
            text = "Fecha Inicio",
            modifier = Modifier.align(Alignment.Start),
            color = SubtitleColorn2,
            style = TittleTopBox
        )
        Button(
            onClick = { mostrarPicker = true },
            modifier = Modifier
                .align(Alignment.Start)
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundBoxColorOne,
                contentColor = Color.Black,
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                Text(
                    text = selectedDate,
                    modifier = Modifier.align(Alignment.CenterStart),
                    color = Color.Black,
                    fontSize = 15.sp
                )
            }
        }
        Spacer(modifier = Modifier.height( 15.dp ))
        Text(
            text = "Periodicidad",
            modifier = Modifier.align(Alignment.Start),
            color = SubtitleColorn2,
            style = TittleTopBox
        )
        RecurrenceSpinner(
            selectedRecurrence = selectedRecurrence,
            onRecurrenceSelected = { selectedRecurrence = it }
        )
        Spacer(modifier = Modifier.height( 15.dp ))
        Text(
            text = "Tipo",
            modifier = Modifier.align(Alignment.Start),
            color = SubtitleColorn2,
            style = TittleTopBox
        )
        TypeMovSpinner(
            selectedTypeMov = selectedTypeMov,
            onSelectedTypeMov = { selectedTypeMov = it }
        )
        Spacer(modifier = Modifier.height( 40.dp ))
        Button(
            onClick = {

                val dateIni = formatDateForDB(selectedDate)

                val movRecur = MovRecur(
                    nombre = name,
                    importe = amount.toDoubleOrNull() ?: 0.0,
                    periodicidade = selectedRecurrence,
                    data_ini = dateIni,
                    data_rnv = calculateNextDate(dateIni, selectedRecurrence!!),
                    tipo = selectedTypeMov
                )

                if (selectedRecurrence != null && selectedTypeMov != null && name != "" && amount != "") {
                    movRecurViewModel.insert(movRecur)
                    Toast.makeText(context, "Movimiento recurrente guardado correctamente", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                } else {
                    Toast.makeText(context, "Debes seleccionar todos los campos", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(250.dp).height(70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundBoxColorGreen,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                text = "GUARDAR",
            )
        }
    }
}