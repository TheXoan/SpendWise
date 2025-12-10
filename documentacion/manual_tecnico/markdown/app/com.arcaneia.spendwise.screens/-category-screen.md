//[app](../../index.md)/[com.arcaneia.spendwise.screens](index.md)/[CategoryScreen](-category-screen.md)

# CategoryScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [CategoryScreen](-category-screen.md)(navController: [NavController](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html), categoriaViewModel: [CategoriaViewModel](../com.arcaneia.spendwise.data.model/-categoria-view-model/index.md))

Pantalla encargada de gestionar las categorías de la aplicación.

Permite:

- 
   Visualizar las categorías existentes mediante un combo box.
- 
   Crear nuevas categorías.
- 
   Actualizar categorías seleccionadas.
- 
   Eliminar categorías.

Esta pantalla utiliza `CategoriaViewModel` para interactuar con la capa de datos, aunque también recurre directamente al DAO cuando es necesario (actualización e inserción).

Elementos principales de la UI:

- 
   Campo para seleccionar una categoría existente (selector).
- 
   Campo de texto para ingresar o modificar el nombre de la categoría.
- 
   Botón para guardar (crear o actualizar).
- 
   Botón para eliminar la categoría seleccionada.

#### Parameters

androidJvm

| | |
|---|---|
| navController | Controlador de navegación para cambiar de pantallas. |
| categoriaViewModel | ViewModel encargado de gestionar la lógica de categorías. |
