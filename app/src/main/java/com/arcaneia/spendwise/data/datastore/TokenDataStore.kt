package com.arcaneia.spendwise.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * DataStore de preferencias utilizado para almacenar información de sesión del usuario.
 *
 * Este archivo define una instancia de DataStore asociada al contexto, donde se guardan:
 * - El token de autenticación.
 * - El ID del usuario.
 *
 * El almacenamiento se realiza de forma asíncrona y segura, usando flujos (`Flow`)
 * para exponer los datos reaccionando a cambios en tiempo real.
 */
val Context.tokenDataStore by preferencesDataStore(name = "user_prefs")

/**
 * Objeto que gestiona la lectura y escritura del token y userId del usuario.
 *
 * Este gestor permite:
 * - Guardar token + userId en la misma operación.
 * - Recuperar ambos como flujos (`Flow<String?>`).
 * - Limpiar la sesión eliminando ambos valores.
 */
object TokenDataStore {

    /** Clave utilizada para almacenar el token JWT en DataStore. */
    private val TOKEN_KEY = stringPreferencesKey("auth_token")

    /** Clave utilizada para almacenar el ID del usuario autenticado. */
    private val USER_ID_KEY = stringPreferencesKey("user_id")

    /**
     * Guarda el token y el userId en una sola operación atómica.
     *
     * @param context Contexto necesario para acceder al DataStore.
     * @param token Token JWT devuelto por el backend.
     * @param userId Identificador del usuario autenticado.
     */
    suspend fun saveSession(context: Context, token: String, userId: String) {
        context.tokenDataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
            prefs[USER_ID_KEY] = userId
        }
    }

    /**
     * Devuelve un flujo que emite el token almacenado.
     *
     * @param context Contexto asociado al DataStore.
     * @return Un flujo que emite el token actual o null si no existe.
     */
    fun getToken(context: Context): Flow<String?> {
        return context.tokenDataStore.data.map { prefs ->
            prefs[TOKEN_KEY]
        }
    }

    /**
     * Devuelve un flujo que emite el userId almacenado.
     *
     * @param context Contexto asociado al DataStore.
     * @return Un flujo que emite el userId actual o null si no está guardado.
     */
    fun getUserId(context: Context): Flow<String?> {
        return context.tokenDataStore.data.map { prefs ->
            prefs[USER_ID_KEY]
        }
    }

    /**
     * Elimina tanto el token como el userId, cerrando la sesión local del usuario.
     *
     * @param context Contexto necesario para acceder al DataStore.
     */
    suspend fun clear(context: Context) {
        context.tokenDataStore.edit { prefs ->
            prefs.remove(TOKEN_KEY)
            prefs.remove(USER_ID_KEY)
        }
    }
}