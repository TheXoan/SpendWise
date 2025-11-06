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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcaneia.spendwise.data.model.Recurrence
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOne
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOneSelected

@Composable
fun RecurrenceSpinner(
    selectedRecurrence: Recurrence?,
    onRecurrenceSelected: (Recurrence) -> Unit
){
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        // Botón principal que muestra el valor seleccionado
        Button(
            onClick = { expanded = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundBoxColorOne,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = selectedRecurrence?.description ?: "Seleccionar periodicidad",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }

        // Menú desplegable con los valores del enum
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth().
                    height(50.dp)
                .background(BackgroundBoxColorOneSelected)
        ) {
            Recurrence.values().forEach { recurrence ->
                DropdownMenuItem(
                    text = { Text(recurrence.description, color = Color.Black) },
                    onClick = {
                        onRecurrenceSelected(recurrence)
                        expanded = false
                    }
                )
            }
        }
    }
}