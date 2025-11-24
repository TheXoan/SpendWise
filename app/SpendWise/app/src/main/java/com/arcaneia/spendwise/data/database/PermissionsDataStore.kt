package com.arcaneia.spendwise.data.database

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// DataStore global para permisos
val Context.permissionsDataStore by preferencesDataStore(name = "permissions_prefs")

// Claves de permisos
object PermissionsKeys {
    val NOTIFICATION = booleanPreferencesKey("notification_permission")
}

class PermissionsDataStore(private val context: Context) {

    // Leer un permiso (genérico)
    fun isGranted(key: Preferences.Key<Boolean>): Flow<Boolean> =
        context.permissionsDataStore.data.map { prefs ->
            prefs[key] ?: false
        }

    // Leer permiso de notificación
    val isNotificationGranted: Flow<Boolean> =
        isGranted(PermissionsKeys.NOTIFICATION)

    // Guardar permiso de notificación
    suspend fun setNotificationGranted(value: Boolean) {
        context.permissionsDataStore.edit { prefs ->
            prefs[PermissionsKeys.NOTIFICATION] = value
        }
    }
}