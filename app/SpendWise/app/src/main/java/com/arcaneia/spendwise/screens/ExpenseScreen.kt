package com.arcaneia.spendwise.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arcaneia.spendwise.data.model.MovViewModel
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOne
import com.arcaneia.spendwise.ui.theme.SubtitleColor
import com.arcaneia.spendwise.ui.theme.SubtitleColorn2
import com.arcaneia.spendwise.ui.theme.SubtitleTextStyle
import com.arcaneia.spendwise.ui.theme.TitleTopBar
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.arcaneia.spendwise.R
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.model.CategoriaViewModel
import com.arcaneia.spendwise.data.model.TypeMov
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOneSelected
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorRed
import com.arcaneia.spendwise.ui.theme.ColorHint
import com.arcaneia.spendwise.ui.theme.TitleBox
import com.arcaneia.spendwise.utils.ComboBoxCategorias
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Pantalla para registrar un nuevo gasto en la aplicación.
 *
 * Esta vista permite al usuario:
 * - Introducir un importe numérico.
 * - Seleccionar una categoría existente.
 * - Añadir una descripción opcional del gasto.
 * - Guardar el movimiento en la base de datos mediante [MovViewModel].
 *
 * La pantalla incorpora validaciones básicas:
 * - El importe solo admite números y decimales.
 * - La categoría es obligatoria.
 *
 * Una vez guardado el gasto, se muestra un `Toast` confirmando la operación
 * y se regresa a la pantalla anterior mediante `navController.popBackStack()`.
 *
 * @param navController Controlador de navegación para movernos entre pantallas.
 * @param movViewModel ViewModel responsable de manejar operaciones relacionadas con movimientos.
 * @param categoriaViewModel ViewModel para gestionar la lista de categorías disponibles.
 */
@Composable
fun ExpenseScreen(
    navController: NavController,
    movViewModel: MovViewModel,
    categoriaViewModel: CategoriaViewModel
) {

    var cantidadGasto by remember { mutableStateOf("") }
    var expenseDescription by remember { mutableStateOf("") }
    var categoriaSeleccionadaId by remember { mutableStateOf<Int?>(null) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(id = R.string.new_expense),
            style = TitleTopBar,
            fontSize = 30.sp,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.add_new_expense),
            style = SubtitleTextStyle,
            color = SubtitleColor,
            fontSize = 15.sp,
        )

        Spacer(modifier = Modifier.height(20.dp))

        // -------- INPUT: CANTIDAD --------
        Text(
            text = stringResource(id = R.string.amount),
            modifier = Modifier.align(Alignment.Start),
            color = SubtitleColorn2
        )

        OutlinedTextField(
            value = cantidadGasto,
            onValueChange = { cantidadGastoMod ->
                // Solo se permiten números y decimales
                if (cantidadGastoMod.matches(Regex("^\\d*\\.?\\d*\$"))) {
                    cantidadGasto = cantidadGastoMod
                }
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
                        modifier = Modifier.padding(start = 4.dp)
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
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .height(120.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = BackgroundBoxColorOne,
                focusedContainerColor = BackgroundBoxColorOneSelected,
                cursorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // -------- INPUT: CATEGORÍA --------
        Text(
            text = stringResource(id = R.string.category),
            modifier = Modifier.align(Alignment.Start),
            color = SubtitleColorn2
        )

        ComboBoxCategorias(
            categoriaViewModel,
            onCategoriaSeleccionada = { id ->
                categoriaSeleccionadaId = id
            },
            internalShape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // -------- INPUT: DESCRIPCIÓN --------
        OutlinedTextField(
            value = expenseDescription,
            onValueChange = { entradaUsuario ->
                expenseDescription = entradaUsuario
            },
            placeholder = { Text(stringResource(id = R.string.description), color = ColorHint, style = TitleBox) },
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

        Spacer(modifier = Modifier.height(35.dp))

        // -------- BOTÓN GUARDAR GASTO --------
        Button(
            onClick = {

                val fechaActual = SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss",
                    Locale.getDefault()
                ).format(Date())

                val mov = Mov(
                    tipo = TypeMov.GASTO,
                    importe = cantidadGasto.toDoubleOrNull() ?: 0.0,
                    data_mov = fechaActual,
                    descricion = expenseDescription,
                    categoria_id = categoriaSeleccionadaId ?: 0,
                    mov_recur_id = null
                )

                if (categoriaSeleccionadaId != null) {
                    movViewModel.insert(mov)
                    Toast.makeText(context, context.getString(R.string.success_save_expense), Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                } else {
                    Toast.makeText(context, context.getString(R.string.must_select_cat_first), Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
                .width(250.dp)
                .height(70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundBoxColorRed,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                context.getString(R.string.save_expense),
                color = Color.White
            )
        }
    }
}