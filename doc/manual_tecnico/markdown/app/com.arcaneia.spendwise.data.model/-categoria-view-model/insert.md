//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[CategoriaViewModel](index.md)/[insert](insert.md)

# insert

[androidJvm]\
fun [insert](insert.md)(cat: [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)): Job

Inserta una categoría **localmente e inmediatamente**, y luego intenta sincronizarla con el servidor.

Flujo de trabajo:

1. 
   Se inserta en Room.
2. 
   Se crea el registro correspondiente en PocketBase.
3. 
   Se actualiza el `remote_id` en la base de datos local.

#### Parameters

androidJvm

| | |
|---|---|
| cat | Categoría que se desea insertar. |
