package com.arcaneia.spendwise.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcaneia.spendwise.data.model.Recurrence
import com.arcaneia.spendwise.data.model.TypeMov
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOne
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOneSelected
import com.arcaneia.spendwise.R

/**
 * Componente que muestra un selector desplegable (`Spinner`) para elegir una opción
 * de la enumeración [Recurrence].
 *
 * El botón principal muestra la periodicidad actualmente seleccionada o un texto
 * predeterminado si no se ha seleccionado ninguna. Al hacer clic, se despliega
 * un menú con todas las opciones disponibles.
 *
 * @param selectedRecurrence Valor actualmente seleccionado de tipo [Recurrence], o `null`
 * si aún no hay selección.
 * @param onRecurrenceSelected Callback que se ejecuta cuando el usuario selecciona
 * una opción del menú, devolviendo la instancia seleccionada.
 */
@Composable
fun RecurrenceSpinner(
    selectedRecurrence: Recurrence?,
    onRecurrenceSelected: (Recurrence) -> Unit
) {
    var expanded by remember {
        mutableStateOf(
            false
        )
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        // Botón principal que muestra el valor seleccionado
        Button(
            onClick = { expanded = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    50.dp
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundBoxColorOne,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = selectedRecurrence?.description
                        ?: stringResource(id = R.string.choose_recurrence),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Start
                )
            }

            // Menú desplegable con los valores del enum
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        BackgroundBoxColorOneSelected
                    )
            ) {
                Recurrence.entries
                    .forEach { recurrence ->
                        DropdownMenuItem(
                            text = {
                                Text(recurrence.description,
                                    color = Color.Black
                                )
                            },
                            onClick = {onRecurrenceSelected(recurrence)
                                expanded =
                                    false
                            }
                        )
                    }
            }
        }
    }
}

/**
 * Componente que muestra un selector desplegable (`Spinner`) para elegir una opción
 * de la enumeración [TypeMov].
 *
 * El botón principal muestra el tipo de movimiento actualmente seleccionado o un texto
 * predeterminado si aún no hay selección. Al hacer clic, se despliega un menú con todas
 * las opciones disponibles.
 *
 * @param selectedTypeMov Valor actualmente seleccionado de tipo [TypeMov], o `null`
 * si aún no hay selección.
 * @param onSelectedTypeMov Callback que se ejecuta cuando el usuario selecciona
 * una opción del menú, devolviendo la instancia seleccionada.
 */
@Composable
fun TypeMovSpinner(
    selectedTypeMov: TypeMov?,
    onSelectedTypeMov: (TypeMov) -> Unit
) {
    var expanded by remember {
        mutableStateOf(
            false
        )
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        // Botón principal que muestra el valor seleccionado
        Button(
            onClick = { expanded = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    50.dp
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundBoxColorOne,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = selectedTypeMov?.description
                        ?: stringResource(id = R.string.choose_type_mov),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Start
                )
            }

            // Menú desplegable con los valores del enum
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false},
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        BackgroundBoxColorOneSelected
                    )
            ) {
                TypeMov.entries
                    .forEach { recurrence ->
                        DropdownMenuItem(
                            text = {
                                Text(recurrence.description,
                                    color = Color.Black
                                )
                            },
                            onClick = {onSelectedTypeMov(recurrence)
                                expanded =
                                    false
                            }
                        )
                    }
            }
        }
    }
}