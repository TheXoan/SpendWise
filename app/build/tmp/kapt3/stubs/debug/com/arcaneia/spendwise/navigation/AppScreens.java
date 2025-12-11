package com.arcaneia.spendwise.navigation;

/**
 * Clase sellada que define todas las rutas de navegación de la aplicación.
 *
 * Cada pantalla de la app se representa como un objeto dentro de esta clase,
 * garantizando que solo existan rutas válidas y centralizando su definición.
 *
 * Al utilizar una `sealed class`, se asegura que las rutas solo puedan ser
 * creadas desde este archivo, evitando inconsistencias y errores tipográficos
 * en el sistema de navegación.
 *
 * @property route Cadena que representa la ruta única utilizada por Navigation Compose.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\n\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\n\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u00a8\u0006\u001c"}, d2 = {"Lcom/arcaneia/spendwise/navigation/AppScreens;", "", "route", "", "<init>", "(Ljava/lang/String;)V", "getRoute", "()Ljava/lang/String;", "MainScreen", "HistoryScreen", "SplashScreen", "ExpenseScreen", "IncomeScreen", "CategoryScreen", "SettingScreen", "MovRecurHistoryScreen", "NewMovRecurScreen", "LoginScreen", "Lcom/arcaneia/spendwise/navigation/AppScreens$CategoryScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens$ExpenseScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens$HistoryScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens$IncomeScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens$LoginScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens$MainScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens$MovRecurHistoryScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens$NewMovRecurScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens$SettingScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens$SplashScreen;", "app_debug"})
public abstract class AppScreens {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String route = null;
    
    private AppScreens(java.lang.String route) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRoute() {
        return null;
    }
    
    /**
     * Pantalla de gestión de categorías.
     */
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/arcaneia/spendwise/navigation/AppScreens$CategoryScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens;", "<init>", "()V", "app_debug"})
    public static final class CategoryScreen extends com.arcaneia.spendwise.navigation.AppScreens {
        @org.jetbrains.annotations.NotNull()
        public static final com.arcaneia.spendwise.navigation.AppScreens.CategoryScreen INSTANCE = null;
        
        private CategoryScreen() {
        }
    }
    
    /**
     * Pantalla para registrar o visualizar gastos.
     */
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/arcaneia/spendwise/navigation/AppScreens$ExpenseScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens;", "<init>", "()V", "app_debug"})
    public static final class ExpenseScreen extends com.arcaneia.spendwise.navigation.AppScreens {
        @org.jetbrains.annotations.NotNull()
        public static final com.arcaneia.spendwise.navigation.AppScreens.ExpenseScreen INSTANCE = null;
        
        private ExpenseScreen() {
        }
    }
    
    /**
     * Pantalla del historial de movimientos.
     */
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/arcaneia/spendwise/navigation/AppScreens$HistoryScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens;", "<init>", "()V", "app_debug"})
    public static final class HistoryScreen extends com.arcaneia.spendwise.navigation.AppScreens {
        @org.jetbrains.annotations.NotNull()
        public static final com.arcaneia.spendwise.navigation.AppScreens.HistoryScreen INSTANCE = null;
        
        private HistoryScreen() {
        }
    }
    
    /**
     * Pantalla para registrar o visualizar ingresos.
     */
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/arcaneia/spendwise/navigation/AppScreens$IncomeScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens;", "<init>", "()V", "app_debug"})
    public static final class IncomeScreen extends com.arcaneia.spendwise.navigation.AppScreens {
        @org.jetbrains.annotations.NotNull()
        public static final com.arcaneia.spendwise.navigation.AppScreens.IncomeScreen INSTANCE = null;
        
        private IncomeScreen() {
        }
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/arcaneia/spendwise/navigation/AppScreens$LoginScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens;", "<init>", "()V", "app_debug"})
    public static final class LoginScreen extends com.arcaneia.spendwise.navigation.AppScreens {
        @org.jetbrains.annotations.NotNull()
        public static final com.arcaneia.spendwise.navigation.AppScreens.LoginScreen INSTANCE = null;
        
        private LoginScreen() {
        }
    }
    
    /**
     * Pantalla principal del resumen general.
     */
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/arcaneia/spendwise/navigation/AppScreens$MainScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens;", "<init>", "()V", "app_debug"})
    public static final class MainScreen extends com.arcaneia.spendwise.navigation.AppScreens {
        @org.jetbrains.annotations.NotNull()
        public static final com.arcaneia.spendwise.navigation.AppScreens.MainScreen INSTANCE = null;
        
        private MainScreen() {
        }
    }
    
    /**
     * Pantalla con el historial de movimientos recurrentes.
     */
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/arcaneia/spendwise/navigation/AppScreens$MovRecurHistoryScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens;", "<init>", "()V", "app_debug"})
    public static final class MovRecurHistoryScreen extends com.arcaneia.spendwise.navigation.AppScreens {
        @org.jetbrains.annotations.NotNull()
        public static final com.arcaneia.spendwise.navigation.AppScreens.MovRecurHistoryScreen INSTANCE = null;
        
        private MovRecurHistoryScreen() {
        }
    }
    
    /**
     * Pantalla para crear un nuevo movimiento recurrente.
     */
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/arcaneia/spendwise/navigation/AppScreens$NewMovRecurScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens;", "<init>", "()V", "app_debug"})
    public static final class NewMovRecurScreen extends com.arcaneia.spendwise.navigation.AppScreens {
        @org.jetbrains.annotations.NotNull()
        public static final com.arcaneia.spendwise.navigation.AppScreens.NewMovRecurScreen INSTANCE = null;
        
        private NewMovRecurScreen() {
        }
    }
    
    /**
     * Pantalla de configuración de la aplicación.
     */
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/arcaneia/spendwise/navigation/AppScreens$SettingScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens;", "<init>", "()V", "app_debug"})
    public static final class SettingScreen extends com.arcaneia.spendwise.navigation.AppScreens {
        @org.jetbrains.annotations.NotNull()
        public static final com.arcaneia.spendwise.navigation.AppScreens.SettingScreen INSTANCE = null;
        
        private SettingScreen() {
        }
    }
    
    /**
     * Pantalla inicial de carga (splash).
     */
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lcom/arcaneia/spendwise/navigation/AppScreens$SplashScreen;", "Lcom/arcaneia/spendwise/navigation/AppScreens;", "<init>", "()V", "app_debug"})
    public static final class SplashScreen extends com.arcaneia.spendwise.navigation.AppScreens {
        @org.jetbrains.annotations.NotNull()
        public static final com.arcaneia.spendwise.navigation.AppScreens.SplashScreen INSTANCE = null;
        
        private SplashScreen() {
        }
    }
}