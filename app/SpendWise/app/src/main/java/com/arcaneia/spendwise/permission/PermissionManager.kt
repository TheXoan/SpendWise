package com.arcaneia.spendwise.permission

import android.Manifest
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


class PermissionManager {

    @Composable
    fun GetNotificationPermission() {

        // Android context
        val context = LocalContext.current

        // DataStore
        val dataStore = remember {
            PermissionsDataStore(
                context
            )
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
                launcher.launch(
                    Manifest.permission.POST_NOTIFICATIONS)
            } else {
                // Si Android < 13, se considera permitido
                scope.launch {
                    dataStore.setNotificationGranted(true)
                }
            }
        }
    }
}