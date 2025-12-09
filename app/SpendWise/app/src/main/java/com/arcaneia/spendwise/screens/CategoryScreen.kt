package com.arcaneia.spendwise.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arcaneia.spendwise.data.database.AppDatabase
import com.arcaneia.spendwise.data.entity.Categoria
import com.arcaneia.spendwise.data.model.CategoriaViewModel
import com.arcaneia.spendwise.ui.theme.BackgroundBoxCategory
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOne
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOneSelected
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorRed
import com.arcaneia.spendwise.ui.theme.ColorHint
import com.arcaneia.spendwise.ui.theme.SubtitleColor
import com.arcaneia.spendwise.ui.theme.SubtitleColorn2
import com.arcaneia.spendwise.ui.theme.SubtitleTextStyle
import com.arcaneia.spendwise.ui.theme.TitleBox
import com.arcaneia.spendwise.ui.theme.TitleTopBar
import com.arcaneia.spendwise.utils.ComboBoxCategorias
import kotlinx.coroutines.launch
import com.arcaneia.spendwise.R

/**
 * Pantalla encargada de gestionar las categorías de la aplicación.
 *
 * Permite:
 * - Visualizar las categorías existentes mediante un combo box.
 * - Crear nuevas categorías.
 * - Actualizar categorías seleccionadas.
 * - Eliminar categorías.
 *
 * Esta pantalla utiliza `CategoriaViewModel` para interactuar con la capa de datos,
 * aunque también recurre directamente al DAO cuando es necesario (actualización e inserción).
 *
 * Elementos principales de la UI:
 * - Campo para seleccionar una categoría existente (selector).
 * - Campo de texto para ingresar o modificar el nombre de la categoría.
 * - Botón para guardar (crear o actualizar).
 * - Botón para eliminar la categoría seleccionada.
 *
 * @param navController Controlador de navegación para cambiar de pantallas.
 * @param categoriaViewModel ViewModel encargado de gestionar la lógica de categorías.
 */
@Composable
fun CategoryScreen(
    navController: NavController,
    categoriaViewModel: CategoriaViewModel
) {

    var idSelectedCategory by remember { mutableStateOf<Int?>(null) }
    var nameCategory by remember { mutableStateOf("") }

    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.categories),
            style = TitleTopBar,
            fontSize = 50.sp,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.add_category),
            style = SubtitleTextStyle,
            color = SubtitleColor,
            fontSize = 15.sp,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.categories),
            modifier = Modifier.align(Alignment.Start),
            color = SubtitleColorn2
        )

        ComboBoxCategorias(
            categoriaViewModel,
            onCategoriaSeleccionada = { id ->
                idSelectedCategory = id
            },
            internalShape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.category_name),
            modifier = Modifier.align(Alignment.Start),
            color = SubtitleColorn2
        )

        OutlinedTextField(
            value = nameCategory,
            onValueChange = { entradaUsuario ->
                nameCategory = entradaUsuario
            },
            placeholder = { Text(stringResource(id = R.string.category_name), color = ColorHint, style = TitleBox) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = BackgroundBoxColorOne,
                focusedContainerColor = BackgroundBoxColorOneSelected,
                cursorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                scope.launch {
                    if (nameCategory != "" && idSelectedCategory != 0 && idSelectedCategory != null)  {
                        val categoria = Categoria(id = idSelectedCategory!!, nome = nameCategory, tipo = "")
                        categoriaViewModel.update(categoria) // 🔹 usar ViewModel
                        Toast.makeText(context, context.getString(R.string.success_category_update), Toast.LENGTH_SHORT).show()
                        nameCategory = ""
                    } else if (nameCategory != "" ){
                        val categoria = Categoria(nome = nameCategory, tipo = "")
                        categoriaViewModel.insert(categoria) // 🔹 usar ViewModel
                        Toast.makeText(context, context.getString(R.string.success_category_created), Toast.LENGTH_SHORT).show()
                        nameCategory = ""
                    } else {
                        Toast.makeText(context, context.getString(R.string.name_cat_cant_empty), Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
                .width(250.dp)
                .height(70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundBoxCategory,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                text = context.getString(R.string.save_cat),
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // BOTÓN PARA ELIMINAR UNA CATEGORÍA
        Button(
            onClick = {
                if (idSelectedCategory != null) {
                    categoriaViewModel.deleteById(idSelectedCategory!!)
                    Toast.makeText(context, context.getString(R.string.success_category_deleted), Toast.LENGTH_SHORT).show()
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
                text = context.getString(R.string.delete_category),
            )
        }
    }
}