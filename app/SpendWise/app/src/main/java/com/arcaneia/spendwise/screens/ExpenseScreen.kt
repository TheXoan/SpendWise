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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arcaneia.spendwise.data.entity.Categoria
import com.arcaneia.spendwise.data.entity.Mov
import com.arcaneia.spendwise.data.model.MovViewModel
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorOne
import com.arcaneia.spendwise.ui.theme.SubtitleColor
import com.arcaneia.spendwise.ui.theme.SubtitleColorn2
import com.arcaneia.spendwise.ui.theme.SubtitleTextStyle
import com.arcaneia.spendwise.ui.theme.TitleTextStyle
import com.arcaneia.spendwise.ui.theme.TitleTopBar
import com.arcaneia.spendwise.utils.ComboBoxGeneric
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ExpenseScreen(
    navController: NavController,
    movViewModel: MovViewModel
){

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Nuevo Gasto",
            style = TitleTopBar,
            fontSize = 30.sp,
            color = Color.White,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Text(
            text = "Agrega una nueva entrada de gasto",
            style = SubtitleTextStyle,
            color = SubtitleColor,
            fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Cantidad",
                    modifier = Modifier.align(Alignment.Start),
                    color = SubtitleColorn2
                )
                Button(
                    onClick = {

                    },
                    modifier = Modifier.fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .height(100.dp)
                        .padding(top = 5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BackgroundBoxColorOne,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(12.dp)
                ){
                Text(
                    text = "0,0€",
                    style = SubtitleTextStyle,
                    color = Color.Black
                )}
            }

        }
}

@Preview(showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ExpenseScreenPreview(){


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