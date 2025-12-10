//[app](../../../index.md)/[com.arcaneia.spendwise.data.model](../index.md)/[CategoriaViewModel](index.md)/[update](update.md)

# update

[androidJvm]\
fun [update](update.md)(cat: [Categoria](../../com.arcaneia.spendwise.data.entity/-categoria/index.md)): Job

Actualiza una categoría tanto local como remotamente.

- 
   Primero se actualiza en la base de datos local.
- 
   Si ya fue sincronizada previamente (tiene `remote_id`), también se actualiza en PocketBase.

Se preserva el `remote_id` original y solo se modifican los campos editables.

#### Parameters

androidJvm

| | |
|---|---|
| cat | Categoría con los nuevos valores a aplicar. |
