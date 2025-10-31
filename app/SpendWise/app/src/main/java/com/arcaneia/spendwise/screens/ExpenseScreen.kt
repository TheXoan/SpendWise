package com.arcaneia.spendwise.screens

import android.content.res.Configuration
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arcaneia.spendwise.data.database.AppDatabase
import com.arcaneia.spendwise.data.entity.Categoria
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.model.MovViewModel
import com.arcaneia.spendwise.data.repository.MovRepository
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOne
import com.arcaneia.spendwise.ui.theme.SpendWiseTheme
import com.arcaneia.spendwise.ui.theme.SubtitleColor
import com.arcaneia.spendwise.ui.theme.SubtitleColorn2
import com.arcaneia.spendwise.ui.theme.SubtitleTextStyle
import com.arcaneia.spendwise.ui.theme.TitleTextStyle
import com.arcaneia.spendwise.ui.theme.TitleTopBar
import com.arcaneia.spendwise.utils.ComboBoxGeneric
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOneSelected
import com.arcaneia.spendwise.ui.theme.TitleBox

@Composable
fun ExpenseScreen(
    navController: NavController,
    movViewModel: MovViewModel,
) {

    var cantidadGasto by remember {mutableStateOf("")}


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
            text = "Nuevo Gasto",
            style = TitleTopBar,
            fontSize = 30.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height( 20.dp ))
        Text(
            text = "Agrega una nueva entrada de gasto",
            style = SubtitleTextStyle,
            color = SubtitleColor,
            fontSize = 15.sp,
        )
        Spacer(modifier = Modifier.height( 20.dp ))

        Text(
            text = "Cantidad",
            modifier = Modifier.align(
                Alignment.Start
            ),
            color = SubtitleColorn2
        )
        OutlinedTextField(
            value = cantidadGasto,
            onValueChange = { cantidadGastoMod ->
                // Solo permite números y decimales separados por punto
                if (cantidadGastoMod.matches(
                        Regex("^\\d*\\.?\\d*\$"))
                ) { cantidadGasto = cantidadGastoMod }
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
            /**  trailingIcon = {                   // 👈 Este texto SIEMPRE se muestra
            Text(
            text = "€",
            color = Color.Black,
            fontSize = 30.sp,
            modifier = Modifier.padding(end = 10.dp)
            )
            },*/
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally).height(120.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = BackgroundBoxColorOne,
                focusedContainerColor = BackgroundBoxColorOneSelected,
                cursorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )



    }

}


@Preview(
    showSystemUi = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ExpenseScreenPreview() {

    //ExpenseScreen()

    /**
    scope.launch(Dispatchers.IO){
    val fechaActual = SimpleDateFormat(
    "yyyy-MM-dd HH:mm:ss",
    Locale.getDefault()
    ).format(
    Date()
    )
    val mov =
    Mov(
    tipo = "gasto",
    importe = 300.25,
    data_mov = fechaActual,
    descricion = "Compra de prueba asdfasdfasdfasdfasdf".chunked(25).joinToString("\n"),
    categoria_id = 1,
    mov_recur_id = null
    )
    db.movDao()
    .insert(
    mov
    )
    }
     */

}