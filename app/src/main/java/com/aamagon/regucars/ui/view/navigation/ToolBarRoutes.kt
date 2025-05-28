package com.aamagon.regucars.ui.view.navigation

sealed class ToolBarRoutes (val route: String) {
    object HomeScreen: ToolBarRoutes("Inicio")
    object CarsScreen: ToolBarRoutes("Coches")
    object MyProfileScreen: ToolBarRoutes("MiPerfil")
    object FiltersScreen: ToolBarRoutes("Filtros")
}