package com.arcaneia.spendwise.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOne
import com.arcaneia.spendwise.ui.theme.TitleBox

@Composable
fun ComboBoxGeneric(
    modifier: Modifier = Modifier,
    label: String,
    options: List<String>,
    selected: String?,
    onSelected: (String) -> Unit,
    enabled: Boolean = true,
){
    var expanded by remember { mutableStateOf(false) }
    val monthNames = mapOf(
        "01" to "Enero", "02" to "Febrero", "03" to "Marzo",
        "04" to "Abril", "05" to "Mayo", "06" to "Junio",
        "07" to "Julio", "08" to "Agosto", "09" to "Septiembre",
        "10" to "Octubre", "11" to "Noviembre", "12" to "Diciembre"
    )
    // Si el valor seleccionado es numérico se muestra el mes correspondiente
    val displayedSelected = monthNames[selected] ?: selected

    Column(
        modifier = modifier,
    ) {
        Box {
            OutlinedButton(
                onClick = { if (enabled) expanded = true },
                enabled = enabled,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = BackgroundBoxColorOne,
                    contentColor = Color.Black
                )

            ) {
                Text(displayedSelected ?: label,
                    color = Color.Black,
                    style = TitleBox
                )

            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { item ->
                    val itemLabel = monthNames[item] ?: item
                    DropdownMenuItem(
                        text = { Text(itemLabel) },
                        onClick = {
                            onSelected(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}