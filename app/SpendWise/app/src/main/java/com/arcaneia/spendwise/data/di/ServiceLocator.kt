package com.arcaneia.spendwise.data.di

import android.content.Context
import com.arcaneia.spendwise.data.database.AppDatabase
import com.arcaneia.spendwise.data.repository.MovRecurRepository

/**
 * Objeto responsable de proporcionar dependencias de forma centralizada,
 * actuando como un Service Locator simple dentro de la aplicación.
 *
 * Su propósito es facilitar la obtención de repositorios y otros componentes
 * relacionados con la capa de datos sin necesidad de un contenedor completo
 * de inyección de dependencias como Hilt o Koin.
 */
object ServiceLocator {

    /**
     * Obtiene una instancia de [MovRecurRepository] utilizando los DAOs necesarios
     * desde la base de datos Room.
     *
     * Crea (o reutiliza) la instancia de [AppDatabase] a través del método
     * `getDatabase(context)` y luego recupera:
     * - `movRecurDao()` para operaciones sobre movimientos recurrentes.
     * - `movDao()` para operaciones sobre movimientos normales.
     *
     * @param context Contexto requerido para inicializar la base de datos.
     * @return Una instancia funcional de [MovRecurRepository] lista para usar.
     */
    fun getMovRecurRepository(context: Context): MovRecurRepository {
        val db = AppDatabase.getDatabase(context)
        return MovRecurRepository(
            movRecurDao = db.movRecurDao(),
            movDao = db.movDao()
        )
    }
}