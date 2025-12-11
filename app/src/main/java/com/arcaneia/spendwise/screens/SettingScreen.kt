package com.arcaneia.spendwise.screens

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arcaneia.spendwise.ui.theme.BackgroundBoxHistory
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.arcaneia.spendwise.data.backup.BackupManager
import com.arcaneia.spendwise.data.datastore.PermissionsDataStore
import com.arcaneia.spendwise.permission.openNotificationSettings
import com.arcaneia.spendwise.ui.theme.ColorTittleTopBox
import com.arcaneia.spendwise.ui.theme.TitleTextLittle
import com.arcaneia.spendwise.ui.theme.TittleTopBox
import kotlinx.coroutines.launch
import com.arcaneia.spendwise.R

/**
 * Pantalla de configuración de la aplicación.
 *
 * Incluye:
 * - Opciones de exportación e importación de datos mediante ZIP.
 * - Control sobre el permiso de notificaciones persistido en DataStore.
 * - Acceso directo a la página de permisos del sistema.
 *
 * Funciones principales:
 * - La importación usa SAF (System Access Framework) con `OpenDocument`.
 * - La exportación usa `CreateDocument` para generar un archivo ZIP.
 * - El permiso de notificaciones se solicita con `RequestPermission`.
 *
 * @param navController Control de navegación, no utilizado directamente en esta pantalla
 *                      pero necesario para mantener la coherencia del sistema de navegación.
 */
@Composable
fun SettingScreen(
    navController: NavController
){
    val context = LocalContext.current
    val backupManager = remember { BackupManager(context) }

    val scope = rememberCoroutineScope()
    var status by remember { mutableStateOf<String?>(null) }

    // DataStore para almacenar permiso de notificaciones
    val permissionsStore = remember {PermissionsDataStore(context)}
    val isEnabled by permissionsStore.isNotificationGranted.collectAsState(initial = false)

    // Launcher de permiso de notificaciones
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted: Boolean ->
        scope.launch {
            permissionsStore.setNotificationGranted(granted)
        }
    }

    // Exportación de datos ZIP
    val exportLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/zip")
    ) { uri ->
        if (uri != null) {
            scope.launch {
                val ok = backupManager.exportWriteToUri(uri)
                status = if (ok) context.getString(R.string.export_ok) else context.getString(R.string.export_failed)
            }
        }
    }

    // Importación de datos ZIP
    val importLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            scope.launch {
                val ok = backupManager.importFromUri(uri)
                status = if (ok) context.getString(R.string.import_ok) else context.getString(R.string.import_failed)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        /** ---------- TÍTULO ---------- **/
        Text(
            text = context.getString(R.string.config),
            style = TitleTextLittle,
            color = Color.White,
            modifier = Modifier.padding(top = 50.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        /** ---------- SECCIÓN DE DATOS ---------- **/
        Text(
            text = context.getString(R.string.data),
            style = TittleTopBox,
            color = ColorTittleTopBox,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    importLauncher.launch(arrayOf("application/zip"))
                },
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .height(60.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackgroundBoxHistory,
                ),
                shape = RoundedCornerShape(40.dp),
            ) {
                Text(
                    context.getString(R.string.import_data),
                    color = Color.White,
                    fontSize = 15.sp
                )
            }
            Button(
                onClick = {
                    exportLauncher.launch("spendwise.zip")
                },
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .height(60.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BackgroundBoxHistory,
                ),
                shape = RoundedCornerShape(40.dp),
            ) {
                Text(
                    context.getString(R.string.export_data),
                    color = Color.White,
                    fontSize = 15.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        /** ---------- SECCIÓN DE PERMISOS ---------- **/
        Text(
            text = context.getString(R.string.permissions),
            style = TittleTopBox,
            color = ColorTittleTopBox,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Etiqueta "Notificaciones"
            Text(
                text = context.getString(R.string.notifications),
                style = TittleTopBox,
                color = Color.White,
                modifier = Modifier
                    .background(
                        color = BackgroundBoxHistory,
                        shape = RoundedCornerShape(50.dp)
                    )
                    .padding(horizontal = 20.dp, vertical = 15.dp)
            )

            // Switch para activar/desactivar permiso
            Switch(
                checked = isEnabled,
                onCheckedChange = { checked ->
                    if (checked) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        } else {
                            scope.launch { permissionsStore.setNotificationGranted(true) }
                        }
                    } else {
                        scope.launch { permissionsStore.setNotificationGranted(false) }
                        openNotificationSettings(context)
                    }
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF4CAF50),
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.Gray,
                )
            )
        }
    }
}