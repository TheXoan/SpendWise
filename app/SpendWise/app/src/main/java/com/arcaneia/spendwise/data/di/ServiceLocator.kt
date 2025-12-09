package com.arcaneia.spendwise.data.di

import android.content.Context
import com.arcaneia.spendwise.data.database.AppDatabase
import com.arcaneia.spendwise.data.dao.MovDao
import com.arcaneia.spendwise.data.dao.MovRecurDao
import com.arcaneia.spendwise.data.repository.MovRecurRepository

/**
 * Proveedor centralizado de dependencias para la capa de datos.
 *
 * Este objeto funciona como un **Service Locator**, permitiendo obtener instancias
 * de repositorios y DAOs sin necesidad de un framework de inyección de dependencias
 * como Hilt o Koin.
 *
 * El principal objetivo de este componente es:
 * - Simplificar la creación de repositorios.
 * - Garantizar el acceso coherente a DAOs de Room.
 * - Evitar duplicación de código en distintas capas de la aplicación.
 *
 * Todas las dependencias provistas aquí utilizan la instancia única de la base
 * de datos generada por [AppDatabase.getDatabase].
 */
object ServiceLocator {

    /**
     * Obtiene una instancia funcional de [MovRecurRepository].
     *
     * Este repositorio requiere dos DAOs:
     * - [MovRecurDao] para gestionar movimientos recurrentes.
     * - [MovDao] para generar movimientos derivados de renovaciones.
     *
     * Además se pasa el `context` como `appContext` para permitir la subida remota
     * desde el propio repositorio cuando se realizan renovaciones automáticas.
     *
     * @param context Contexto utilizado para obtener la base de datos.
     * @return Instancia lista para usar de [MovRecurRepository].
     */
    fun getMovRecurRepository(context: Context): MovRecurRepository {
        val db = AppDatabase.getDatabase(context)
        return MovRecurRepository(
            movRecurDao = db.movRecurDao(),
            movDao = db.movDao(),
            appContext = context
        )
    }

    /**
     * Obtiene el DAO encargado de los movimientos simples ([MovDao]).
     *
     * Útil en procesos como:
     * - sincronización,
     * - generación de notificaciones,
     * - manipulación directa de movimientos.
     *
     * @param context Contexto necesario para resolver la base de datos.
     * @return Instancia de [MovDao].
     */
    fun getMovDao(context: Context): MovDao {
        return AppDatabase.getDatabase(context).movDao()
    }

    /**
     * Obtiene el DAO para movimientos recurrentes ([MovRecurDao]).
     *
     * Este DAO se utiliza para:
     * - gestionar las reglas de recurrencia,
     * - calcular renovaciones,
     * - actualizar fechas de próxima ejecución.
     *
     * @param context Contexto necesario para obtener la instancia de Room.
     * @return Instancia de [MovRecurDao].
     */
    fun getMovRecurDao(context: Context): MovRecurDao {
        return AppDatabase.getDatabase(context).movRecurDao()
    }
}