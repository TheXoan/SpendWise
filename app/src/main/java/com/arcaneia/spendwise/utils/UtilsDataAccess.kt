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
import androidx.compose.ui.res.stringResource
import com.arcaneia.spendwise.R
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

/**
 * ComboBox reutilizable para la selección de **años o meses** dentro de la pantalla de historial.
 *
 * Este componente es genérico y permite recibir una lista de valores de tipo `String`, que pueden
 * representar tanto años (e.g., `"2023"`, `"2024"`) como meses numéricos (e.g., `"01"`, `"02"`).
 *
 * ## Características principales
 * - Traducción automática de números de mes a su nombre correspondiente según el
 *   **idioma del dispositivo** utilizando `Locale.getDefault()`.
 * - Detección inteligente:
 *   - Si el valor está entre **1 y 12**, se interpreta como mes y se traduce.
 *   - Si el valor no representa un mes válido (e.g. un año), se muestra tal cual.
 * - Componente visual estilizado según el tema de la aplicación.
 * - Manejo interno del estado de expansión del menú desplegable.
 * - Admite modo deshabilitado (`enabled = false`).
 *
 * ## Parámetros
 * @param modifier Modificador opcional para personalizar la apariencia externa del componente.
 * @param label Texto mostrado como placeholder cuando no hay selección.
 * @param options Lista de elementos a mostrar en el menú desplegable. Pueden ser meses o años.
 * @param selected Elemento actualmente seleccionado. Puede ser nulo.
 * @param onSelected Callback invocado cuando el usuario selecciona un valor. Devuelve el valor elegido.
 * @param enabled Permite habilitar o deshabilitar la interacción con el ComboBox.
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

    // Función que convierte mes → nombre traducido
    fun translateMonth(value: String): String? {
        val number = value.toIntOrNull() ?: return null
        return if (number in 1..12) {
            Month.of(number)
                .getDisplayName(
                    TextStyle.FULL, Locale.getDefault())
                .replaceFirstChar { it.uppercase() }
        } else null
    }

    val displayedSelected =
        translateMonth(selected ?: "") ?: selected ?: label

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

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { item ->

                val itemLabel =
                    translateMonth(item) ?: item // <-- si no es mes, muestra el número

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
 * ComboBox especializado para mostrar y seleccionar una **categoría** proveniente del
 * `CategoriaViewModel`.
 *
 * Este componente observa automáticamente el flujo de categorías y muestra todas las disponibles.
 * Al seleccionar una categoría, se devuelve su **ID**, permitiendo integrar fácilmente esta
 * selección dentro de la lógica del ViewModel o de la capa de datos.
 *
 * ## Características principales
 * - Lectura automática de categorías desde un `StateFlow` del `CategoriaViewModel`.
 * - Muestra el nombre de cada categoría y mantiene el estado de selección internamente.
 * - Devuelve únicamente el **ID de la categoría** seleccionada.
 * - Estilizado acorde al tema de la interfaz.
 * - Permite personalizar la forma del campo mediante `internalShape`.
 *
 * ## Parámetros
 * @param viewModel Instancia del `CategoriaViewModel` desde donde se obtienen las categorías.
 * @param onCategoriaSeleccionada Callback invocado al seleccionar una categoría, devolviendo su ID.
 * @param internalShape Forma personalizada (e.g., `RoundedCornerShape`) para el campo de texto.
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
            placeholder = { Text(stringResource(id = R.string.category), color = Color.Black, style = TitleBox) },
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