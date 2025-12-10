//[app](../../../index.md)/[com.arcaneia.spendwise.navigation](../index.md)/[AppScreens](index.md)

# AppScreens

sealed class [AppScreens](index.md)

Clase sellada que define todas las rutas de navegación de la aplicación.

Cada pantalla de la app se representa como un objeto dentro de esta clase, garantizando que solo existan rutas válidas y centralizando su definición.

Al utilizar una `sealed class`, se asegura que las rutas solo puedan ser creadas desde este archivo, evitando inconsistencias y errores tipográficos en el sistema de navegación.

#### Inheritors

| |
|---|
| [MainScreen](-main-screen/index.md) |
| [HistoryScreen](-history-screen/index.md) |
| [SplashScreen](-splash-screen/index.md) |
| [ExpenseScreen](-expense-screen/index.md) |
| [IncomeScreen](-income-screen/index.md) |
| [CategoryScreen](-category-screen/index.md) |
| [SettingScreen](-setting-screen/index.md) |
| [MovRecurHistoryScreen](-mov-recur-history-screen/index.md) |
| [NewMovRecurScreen](-new-mov-recur-screen/index.md) |
| [LoginScreen](-login-screen/index.md) |

## Types

| Name | Summary |
|---|---|
| [CategoryScreen](-category-screen/index.md) | [androidJvm]<br>object [CategoryScreen](-category-screen/index.md) : [AppScreens](index.md)<br>Pantalla de gestión de categorías. |
| [ExpenseScreen](-expense-screen/index.md) | [androidJvm]<br>object [ExpenseScreen](-expense-screen/index.md) : [AppScreens](index.md)<br>Pantalla para registrar o visualizar gastos. |
| [HistoryScreen](-history-screen/index.md) | [androidJvm]<br>object [HistoryScreen](-history-screen/index.md) : [AppScreens](index.md)<br>Pantalla del historial de movimientos. |
| [IncomeScreen](-income-screen/index.md) | [androidJvm]<br>object [IncomeScreen](-income-screen/index.md) : [AppScreens](index.md)<br>Pantalla para registrar o visualizar ingresos. |
| [LoginScreen](-login-screen/index.md) | [androidJvm]<br>object [LoginScreen](-login-screen/index.md) : [AppScreens](index.md) |
| [MainScreen](-main-screen/index.md) | [androidJvm]<br>object [MainScreen](-main-screen/index.md) : [AppScreens](index.md)<br>Pantalla principal del resumen general. |
| [MovRecurHistoryScreen](-mov-recur-history-screen/index.md) | [androidJvm]<br>object [MovRecurHistoryScreen](-mov-recur-history-screen/index.md) : [AppScreens](index.md)<br>Pantalla con el historial de movimientos recurrentes. |
| [NewMovRecurScreen](-new-mov-recur-screen/index.md) | [androidJvm]<br>object [NewMovRecurScreen](-new-mov-recur-screen/index.md) : [AppScreens](index.md)<br>Pantalla para crear un nuevo movimiento recurrente. |
| [SettingScreen](-setting-screen/index.md) | [androidJvm]<br>object [SettingScreen](-setting-screen/index.md) : [AppScreens](index.md)<br>Pantalla de configuración de la aplicación. |
| [SplashScreen](-splash-screen/index.md) | [androidJvm]<br>object [SplashScreen](-splash-screen/index.md) : [AppScreens](index.md)<br>Pantalla inicial de carga (splash). |

## Properties

| Name | Summary |
|---|---|
| [route](route.md) | [androidJvm]<br>val [route](route.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>Cadena que representa la ruta única utilizada por Navigation Compose. |
