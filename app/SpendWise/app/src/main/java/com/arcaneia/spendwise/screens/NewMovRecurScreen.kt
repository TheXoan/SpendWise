package com.arcaneia.spendwise.screens

import android.app.DatePickerDialog
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.model.MovRecurViewModel
import com.arcaneia.spendwise.data.model.Recurrence
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorGreen
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOne
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOneSelected
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorRed
import com.arcaneia.spendwise.ui.theme.SubtitleColorn2
import com.arcaneia.spendwise.ui.theme.TitleBox
import com.arcaneia.spendwise.ui.theme.TitleTopBar
import com.arcaneia.spendwise.utils.ComboBoxCategorias
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun NewMovRecurScreen(
    navController: NavController,
    movRecurViewModel: MovRecurViewModel
){

    val context = LocalContext.current

    var amount by remember {mutableStateOf("")}
    var name by remember {mutableStateOf("")}

    // FECHA
    val dateFormat = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }
    val calendar = Calendar.getInstance()
    var selectedDate by remember { mutableStateOf(dateFormat.format(Date())) }
    val datePickerDialog =
        DatePickerDialog(context,{ _, year, month, day -> calendar.set(year, month,day)
                selectedDate = dateFormat.format(calendar.time)
            },
            calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)
        )

    var selectedRecurrence by remember { mutableStateOf<Recurrence?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 30.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Nuevo movimiento recurrente",
            style = TitleTopBar,
            fontSize = 30.sp,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height( 50.dp ))

        Text(
            text = "Importe",
            modifier = Modifier.align(Alignment.Start),
            color = SubtitleColorn2
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
            color = SubtitleColorn2
        )
        OutlinedTextField(
            value = name,
            onValueChange = { userInput ->
                name = userInput
            },
            placeholder = { Text("Nombre gasto recurrente", color = Color.Black, style = TitleBox) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
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
            text = "Fecha Inicio 📅",
            modifier = Modifier.align(Alignment.Start),
            color = SubtitleColorn2
        )
        Button(
            onClick = { datePickerDialog.show() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundBoxColorOne,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = selectedDate,
                    modifier = Modifier.align(
                        Alignment.Center
                    ),
                    color = Color.Black,
                    fontSize = 20.sp
                )
            }
        }
        Spacer(modifier = Modifier.height( 15.dp ))
        Text(
            text = "Periodicidad",
            modifier = Modifier.align(Alignment.Start),
            color = SubtitleColorn2
        )
        RecurrenceSpinner(
            selectedRecurrence = selectedRecurrence,
            onRecurrenceSelected = { selectedRecurrence = it }
        )
        Spacer(modifier = Modifier.height( 40.dp ))
        Button(
            onClick = {
                /*val fechaActual = SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss",
                    Locale.getDefault()
                ).format(Date())

                val mov = Mov(
                    tipo = "gasto",
                    importe = cantidadGasto.toDoubleOrNull() ?: 0.0,
                    data_mov = fechaActual,
                    descricion = expenseDescription,
                    categoria_id = categoriaSeleccionadaId ?: 0,
                    mov_recur_id = null
                )

                if (categoriaSeleccionadaId != null) {
                    movViewModel.insert(mov)
                    Toast.makeText(context, "Gasto guardado correctamente", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                } else {
                    Toast.makeText(context, "Debes seleccionar una categoría", Toast.LENGTH_SHORT).show()
                }*/
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
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