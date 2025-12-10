//[app](../../index.md)/[com.arcaneia.spendwise.ui.theme](index.md)/[SpendWiseTheme](-spend-wise-theme.md)

# SpendWiseTheme

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [SpendWiseTheme](-spend-wise-theme.md)(darkTheme: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) = true, dynamicColor: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) = false, content: @[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html))

Tema principal de la aplicación **SpendWise** basado en Material 3.

Esta función:

- 
   Aplica automáticamente el tema claro u oscuro según el parámetro [darkTheme](-spend-wise-theme.md).
- 
   Permite habilitar *Material You* dinámico en Android 12+ mediante [dynamicColor](-spend-wise-theme.md).
- 
   Utiliza los esquemas de color definidos en LightColors y DarkColors.
- 
   Aplica la tipografía personalizada mediante [SpendWiseTypography](-spend-wise-typography.md).

#### Parameters

androidJvm

| | |
|---|---|
| darkTheme | Indica si debe usarse el tema oscuro.     Por defecto está en `true`, aunque puede vincularse a     isSystemInDarkTheme si se quiere que siga el sistema. |
| dynamicColor | Habilita el uso de colores dinámicos (Material You)     siempre que el dispositivo lo soporte (Android 12+).     Actualmente está desactivado por defecto. |
| content | Contenido composable al que se le aplicará este tema.<br>Notas:<br>-     Si [dynamicColor](-spend-wise-theme.md) es `true`, el sistema genera automáticamente una paleta basada en el fondo de pantalla del usuario. -     El bloque que comenta el comportamiento dinámico está desactivado según el comentario del autor del código. |
