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
import com.arcaneia.spendwise.ui.theme.SubtitleColor
import com.arcaneia.spendwise.ui.theme.SubtitleColorn2
import com.arcaneia.spendwise.ui.theme.SubtitleTextStyle
import com.arcaneia.spendwise.ui.theme.TitleBox
import com.arcaneia.spendwise.ui.theme.TitleTopBar
import com.arcaneia.spendwise.utils.ComboBoxCategorias
import kotlinx.coroutines.launch


@Composable
fun CategoryScreen(
    navController: NavController,
    categoriaViewModel: CategoriaViewModel
) {

    var categoriaSeleccionadaId by remember { mutableStateOf<Int?>(null) }

    var nameCategory by remember { mutableStateOf("") }

    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 30.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Categorías",
            style = TitleTopBar,
            fontSize = 50.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height( 20.dp ))
        Text(
            text = "Agrega una nueva categoría",
            style = SubtitleTextStyle,
            color = SubtitleColor,
            fontSize = 15.sp,
        )
        Spacer(modifier = Modifier.height( 20.dp ))
        Text(
            text = "Categorías",
            modifier = Modifier.align(Alignment.Start),
            color = SubtitleColorn2
        )
        ComboBoxCategorias(
            categoriaViewModel,
            onCategoriaSeleccionada = { id ->
                categoriaSeleccionadaId = id
            }
        )
        Spacer(modifier = Modifier.height( 20.dp ))
        Text(
            text = "Nombre Categoría",
            modifier = Modifier.align(Alignment.Start),
            color = SubtitleColorn2
        )
        OutlinedTextField(
            value = nameCategory,
            onValueChange = { entradaUsuario ->
                nameCategory = entradaUsuario
            },
            placeholder = { Text("Nombre Categoría", color = Color.Gray, style = TitleBox) },
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
        Spacer(modifier = Modifier.height(50.dp))
        Button(
            onClick = {
                scope.launch {
                    if (nameCategory != ""  && categoriaSeleccionadaId != 0 && categoriaSeleccionadaId != null)  {
                        val categoria = Categoria(id = categoriaSeleccionadaId!!, nome = nameCategory, tipo = "")
                        db.categoriaDao().update(categoria)
                        Toast.makeText(context, "Categoría actualizada correctamente", Toast.LENGTH_SHORT).show()
                        nameCategory = ""
                    } else if (nameCategory != "" ){
                        val categoria = Categoria(nome = nameCategory, tipo = "")
                        db.categoriaDao().insert(categoria)
                        Toast.makeText(context, "Categoría creada correctamente", Toast.LENGTH_SHORT).show()
                        nameCategory = ""
                    }else {
                        Toast.makeText(context, "El nombre de la categoría no puede estar vacío", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
                .width(250.dp).height(70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundBoxCategory,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                text = "GUARDAR CATEGORIA",
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (categoriaSeleccionadaId != null) {
                    categoriaViewModel.deleteById(categoriaSeleccionadaId!!)
                    Toast.makeText(context, "Categoría eliminada correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Selecciona una categoría primero", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
                .width(250.dp).height(70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundBoxColorRed,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                text = "BORRAR CATEGORIA",
            )
        }
    }
}