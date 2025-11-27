package com.arcaneia.spendwise.permission

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.arcaneia.spendwise.data.database.PermissionsDataStore
import kotlinx.coroutines.launch
import android.content.Intent
import android.provider.Settings

/**
 * Clase encargada de gestionar la solicitud de permisos relacionados con
 * notificaciones dentro de la aplicación.
 *
 * Utiliza un mecanismo basado en Compose (`rememberLauncherForActivityResult`)
 * junto con DataStore para persistir si el permiso fue concedido o no.
 *
 * Esta clase abstrae toda la lógica necesaria para solicitar el permiso
 * `POST_NOTIFICATIONS` en Android 13+ y para marcarlo como concedido automáticamente
 * en versiones anteriores donde dicho permiso no es requerido.
 */
class PermissionManager {

    /**
     * Composable que solicita automáticamente el permiso de notificaciones
     * al iniciarse.
     *
     * Funciona del siguiente modo:
     *
     * - En **Android 13+ (API 33)**: lanza un diálogo de sistema solicitando el permiso
     *   `POST_NOTIFICATIONS`.
     * - En versiones anteriores: guarda automáticamente el permiso como concedido.
     *
     * Sin importar el resultado, este se guarda en DataStore mediante [PermissionsDataStore].
     *
     * Este composable no dibuja nada en pantalla: solo gestiona la solicitud del permiso.
     */
    @Composable
    fun GetNotificationPermission() {

        // Android context
        val context = LocalContext.current

        // DataStore
        val dataStore = remember {
            PermissionsDataStore(context)
        }

        // Corrutina
        val scope = rememberCoroutineScope()

        // Launcher del permiso
        val launcher = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->

            // Almacenar el resultado en DataStore
            scope.launch {
                dataStore.setNotificationGranted(granted)
            }
        }

        // Efecto para lanzarlo automáticamente (solo una vez)
        LaunchedEffect(Unit) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                // Si Android < 13, se considera permitido
                scope.launch {
                    dataStore.setNotificationGranted(true)
                }
            }
        }
    }
}

/**
 * Abre la pantalla de ajustes del sistema donde el usuario puede gestionar manualmente
 * los permisos de notificaciones de la aplicación.
 *
 * @param context Contexto necesario para iniciar el Intent hacia los ajustes.
 */
fun openNotificationSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
        putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
    }
    context.startActivity(intent)
}