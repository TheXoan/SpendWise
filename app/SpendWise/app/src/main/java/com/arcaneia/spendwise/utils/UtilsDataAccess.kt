package com.arcaneia.spendwise.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.arcaneia.spendwise.data.entity.Categoria
import com.arcaneia.spendwise.data.model.CategoriaViewModel
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOne
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOneSelected
import com.arcaneia.spendwise.ui.theme.TitleBox
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

/**
 * Combo Box para los años y meses que filtran el historial de movimientos
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComboBoxHistory(
    modifier: Modifier = Modifier,
    label: String,
    options: List<String>,
    selected: String?,
    onSelected: (String) -> Unit,
    enabled: Boolean = true,
) {
    var expanded by remember { mutableStateOf(false) }

    val monthNames = mapOf(
        "01" to "Enero", "02" to "Febrero", "03" to "Marzo",
        "04" to "Abril", "05" to "Mayo", "06" to "Junio",
        "07" to "Julio", "08" to "Agosto", "09" to "Septiembre",
        "10" to "Octubre", "11" to "Noviembre", "12" to "Diciembre"
    )

    val displayedSelected = monthNames[selected] ?: selected ?: label

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { if (enabled) expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = displayedSelected,
            onValueChange = {},
            placeholder = { Text(label, color = Color.Black, style = TitleBox) },
            enabled = enabled,
            textStyle = TitleBox,
            trailingIcon = {
                Icon(
                    imageVector = if (expanded)
                        Icons.Outlined.ExpandLess
                    else
                        Icons.Outlined.ExpandMore,
                    contentDescription = null,
                    tint = Color.Black
                )
            },
            modifier = Modifier
                .menuAnchor(
                    type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                    enabled = enabled
                )
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = BackgroundBoxColorOne,
                focusedContainerColor = BackgroundBoxColorOneSelected,
                cursorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )

        ExposedDropdownMenu(
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
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

/**
 * Combo box para mostrar todas las categorías disponibles
 */
@OptIn(
    ExperimentalMaterial3Api::class)
@Composable
fun ComboBoxCategorias(
    viewModel: CategoriaViewModel,
    onCategoriaSeleccionada: (Int) -> Unit,
    internalShape: Shape
) {
    val categorias by viewModel.categorias.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var selectedCategoria by remember { mutableStateOf<Categoria?>(null) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor(
                    type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                    enabled = true)
                .fillMaxWidth(),
            readOnly = true,
            value = selectedCategoria?.nome ?: "",
            onValueChange = {},
            placeholder = { Text("Categoría", color = Color.Black, style = TitleBox) }, shape = internalShape,
            trailingIcon = {
                Icon(
                    imageVector = if (expanded)
                        Icons.Outlined.ExpandLess
                    else
                        Icons.Outlined.ExpandMore,
                    contentDescription = null,
                    tint = Color.Black
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = BackgroundBoxColorOne,
                focusedContainerColor = BackgroundBoxColorOneSelected,
                cursorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }, shape = internalShape
        ) {
            categorias.forEach { categoria ->
                DropdownMenuItem(
                    text = { Text(categoria.nome) },
                    onClick = {
                        selectedCategoria = categoria
                        expanded = false
                        onCategoriaSeleccionada(categoria.id)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}