package com.arcaneia.spendwise.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arcaneia.spendwise.R
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorGreen
import com.arcaneia.spendwise.ui.theme.BackgroundBoxColorRed

/**
 * Muestra un cuadro de diálogo con opciones para **editar** o **eliminar**
 * un elemento, junto con la posibilidad de **cerrar** el diálogo.
 *
 * Este componente utiliza `AlertDialog` de Jetpack Compose y presenta un título,
 * un texto descriptivo y dos botones de acción principales.
 *
 * @param title Título que se mostrará en la parte superior del diálogo.
 * @param onEdit Acción que se ejecutará cuando el usuario presione el botón **Editar**.
 * @param onDelete Acción que se ejecutará cuando el usuario presione el botón **Eliminar**.
 * @param onDismiss Acción que se invoca cuando el diálogo se cierra sin seleccionar ninguna acción.
 */
@Composable
fun EditarEliminar(
    title: String,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onDismiss: () -> Unit
){
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.choose_action),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = onEdit,
                    colors = ButtonDefaults.buttonColors(containerColor = BackgroundBoxColorGreen),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.width(120.dp)
                ) {
                    Text(stringResource(id = R.string.edit), color = Color.Black)
                }

                Spacer(modifier = Modifier.width(20.dp))

                Button(
                    onClick = onDelete,
                    colors = ButtonDefaults.buttonColors(containerColor = BackgroundBoxColorRed),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.width(120.dp)
                ) {
                    Text(stringResource(id = R.string.delete), color = Color.Black)
                }
            }
        }
    )
}