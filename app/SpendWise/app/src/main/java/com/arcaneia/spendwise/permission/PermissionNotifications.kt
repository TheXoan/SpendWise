package com.arcaneia.spendwise.permission

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun NotificationPermissionButton(
    launcher: ActivityResultLauncher<String>
) {
    Button(onClick = {
        launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }) {
        Text("PERMISO")
    }
}