package com.example.navegacio

sealed class Routes(val route: String) {
    // Definim la ruta per accedir a la primera pantalla sense paràmetres
    object Pantalla1 : Routes("pantalla1")

    // Definim la ruta per accedir a la segona pantalla amb el paràmetre 'nom'
    object Pantalla2 : Routes("pantalla2/{nom}") {
        fun createRoute(nom: String) = "pantalla2/$nom"
    }

    // Definim la ruta per accedir a la tercera pantalla amb el paràmetres 'nom', 'salutacio', 'edat'
    object Pantalla3 : Routes("pantalla3/{nom}/{salutacio}/{edat}") {
        fun createRoute(nom: String, salutacio: String, edat: Int) = "pantalla3/$nom/$salutacio/$edat"
    }

}