//[app](../../../../index.md)/[com.arcaneia.spendwise.data.database](../../index.md)/[AppDatabase](../index.md)/[Companion](index.md)/[clearInstance](clear-instance.md)

# clearInstance

[androidJvm]\
fun [clearInstance](clear-instance.md)()

Limpia la instancia singleton, permitiendo que se vuelva a generar una nueva cuando se invoque nuevamente [getDatabase](get-database.md).

Esta función es útil en escenarios como restauraciones de backup donde se requiere forzar la recreación de la base de datos.
