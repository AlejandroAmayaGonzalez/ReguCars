package com.aamagon.regucars.ui.navigation

sealed class ToolBarRoutes (val route: String) {
    object HomeScreen: ToolBarRoutes("Inicio")
    object CarsScreen: ToolBarRoutes("Coches")
    object MyProfileScreen: ToolBarRoutes("MiPerfil")
}