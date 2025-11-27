package com.arcaneia.spendwise.data.database

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * DataStore global utilizado para almacenar el estado de permisos de la aplicación.
 *
 * Se crea mediante `preferencesDataStore`, el cual genera automáticamente un
 * DataStore de tipo Preferences asociado al contexto.
 *
 * Nombre del archivo interno: `"permissions_prefs"`.
 */
val Context.permissionsDataStore by preferencesDataStore(name = "permissions_prefs")

/**
 * Objeto que contiene todas las claves utilizadas para almacenar permisos en DataStore.
 *
 * Actualmente incluye:
 * - [NOTIFICATION]: Indica si el permiso de notificaciones ha sido otorgado.
 */
object PermissionsKeys {
    /** Clave usada para almacenar el estado del permiso de notificaciones. */
    val NOTIFICATION = booleanPreferencesKey("notification_permission")
}

/**
 * Clase encargada de gestionar la lectura y escritura de permisos persistentes usando DataStore.
 *
 * Permite consultar y actualizar permisos específicos, incluyendo un acceso directo
 * para el permiso de notificaciones.
 *
 * @property context Contexto necesario para acceder al DataStore de preferencias.
 */
class PermissionsDataStore(private val context: Context) {

    /**
     * Obtiene el estado de un permiso almacenado bajo la clave indicada.
     *
     * @param key Clave del permiso almacenado en DataStore.
     * @return Un `Flow<Boolean>` que emite `true` o `false` dependiendo del estado del permiso.
     *         Si la clave no existe, devuelve `false` por defecto.
     */
    fun isGranted(key: Preferences.Key<Boolean>): Flow<Boolean> =
        context.permissionsDataStore.data.map { prefs ->
            prefs[key] ?: false
        }

    /**
     * Flujo que expone en tiempo real si el permiso de notificaciones ha sido otorgado.
     *
     * Internamente reutiliza [isGranted] para consultar la clave [PermissionsKeys.NOTIFICATION].
     */
    val isNotificationGranted: Flow<Boolean> =
        isGranted(PermissionsKeys.NOTIFICATION)

    /**
     * Guarda el estado del permiso de notificaciones en DataStore.
     *
     * @param value `true` si el permiso ha sido otorgado, `false` de lo contrario.
     */
    suspend fun setNotificationGranted(value: Boolean) {
        context.permissionsDataStore.edit { prefs ->
            prefs[PermissionsKeys.NOTIFICATION] = value
        }
    }
}