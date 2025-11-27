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
 * ComboBox reutilizable para seleccionar **años y meses** en la pantalla de historial.
 *
 * Proporciona:
 * - Visual adaptada al tema de la app
 * - Conversión automática de números de mes a nombre en español
 * - Estado controlado internamente para expansión y selección
 *
 * @param modifier Modificador opcional para el componente.
 * @param label Texto mostrado cuando no hay selección (placeholder).
 * @param options Lista de elementos disponibles (e.g. ["2024", "2025"] o ["01", "02"]).
 * @param selected Elemento actualmente seleccionado, puede ser nulo.
 * @param onSelected Callback que devuelve la opción seleccionada.
 * @param enabled Habilita o deshabilita el combo box.
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

    // Traducción de número de mes → nombre en español
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
        // Campo principal
        OutlinedTextField(
            readOnly = true,
            value = displayedSelected,
            onValueChange = {},
            placeholder = { Text(label, color = Color.Black, style = TitleBox) },
            enabled = enabled,
            textStyle = TitleBox,
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
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

        // Menú desplegable
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
 * ComboBox para mostrar y seleccionar una **categoría** desde el ViewModel.
 *
 * Características:
 * - Lee automáticamente las categorías del `CategoriaViewModel`
 * - Devuelve el **ID** de la categoría seleccionada
 * - Mantiene el estado visual y de selección internamente
 *
 * @param viewModel ViewModel que expone el flujo de categorías.
 * @param onCategoriaSeleccionada Callback que devuelve el id seleccionado.
 * @param internalShape Permite personalizar la forma del campo (RoundedCornerShape, etc.)
 */
@OptIn(ExperimentalMaterial3Api::class)
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
        // Campo principal
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor(
                    type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                    enabled = true
                )
                .fillMaxWidth(),
            readOnly = true,
            value = selectedCategoria?.nome ?: "",
            onValueChange = {},
            placeholder = { Text("Categoría", color = Color.Black, style = TitleBox) },
            shape = internalShape,
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
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

        // Menú de categorías
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            shape = internalShape
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