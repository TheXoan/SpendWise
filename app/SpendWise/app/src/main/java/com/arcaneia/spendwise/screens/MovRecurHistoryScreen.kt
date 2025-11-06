package com.arcaneia.spendwise.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arcaneia.spendwise.data.entity.MovRecur
import com.arcaneia.spendwise.data.model.MovRecurViewModel
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorGreen
import com.arcaneia.spendwise.ui.theme.BackgroundBoxHistory
import com.arcaneia.spendwise.ui.theme.TextBoxBold
import com.arcaneia.spendwise.ui.theme.TitleBox
import com.arcaneia.spendwise.ui.theme.TitleTopBar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun MovRecurHistoryScreen(
    navController: NavController,
    movRecurViewModel: MovRecurViewModel
){

    val movsRecur by movRecurViewModel.movRecurList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 30.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Movimientos recurrentes",
            style = TitleTopBar,
            fontSize = 50.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height( 50.dp ))


        MovRecurList(
            movsRecur = movsRecur,
            modifier = Modifier.fillMaxSize().fillMaxWidth()
                .weight(1f)
        )

        Spacer(modifier = Modifier.height( 50.dp ))

        Button(
            onClick = {
                navController.navigate("newMovRecur_screen")
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp)
                .width(250.dp).height(70.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BackgroundBoxColorGreen,
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                text = "Nuevo",
            )
        }
    }
}

@Composable
fun MovRecurList(
    movsRecur: List<MovRecur>,
    modifier: Modifier = Modifier
) {
    if (movsRecur.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No hay movimientos recurrentes configurados",
                color = Color.Gray
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(movsRecur) { mov ->
                MovRecurItem(mov)
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}

@SuppressLint("SimpleDateFormat",
    "DefaultLocale"
)
@Composable
fun MovRecurItem(mov: MovRecur) {
    Surface(
        color = BackgroundBoxHistory,
        shape = RoundedCornerShape(50.dp),
        tonalElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = mov.nombre,
                    color = Color.White,
                    style = TextBoxBold,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.height(3.dp))

                // Fecha de inicio
                Text(
                    text = "Inicio: " + SimpleDateFormat(
                        "dd/MM/yyyy"
                    )
                        .format(
                            Date(
                                mov.data_ini
                            )
                        ),
                    color = Color.Gray,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )

                Spacer(modifier = Modifier.height(3.dp))

                // Fecha de renovación
                Text(
                    text = "Renovación: " + SimpleDateFormat("dd/MM/yyyy")
                        .format(Date(mov.data_rnv)),
                    color = Color.Gray,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )

                Spacer(modifier = Modifier.height(5.dp))

                // Periodicidad
                Text(
                    text = mov.periodicidade.description
                        .replaceFirstChar { it.titlecase(Locale.getDefault()) },
                    color = Color.Black,
                    style = TextBoxBold,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .background(
                            color = Color(0xA9FFD900),
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                val importeTexto = String.format("%.2f€", mov.importe)
                val fontSize = when {
                    importeTexto.length > 15 -> 11.sp
                    importeTexto.length > 10 -> 12.sp
                    else -> 15.sp
                }
                Text(
                    text = importeTexto,
                    color = Color(0xFF4CAF50), // Verde para recurrentes
                    style = TitleBox,
                    fontSize = fontSize,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
        }
    }
}