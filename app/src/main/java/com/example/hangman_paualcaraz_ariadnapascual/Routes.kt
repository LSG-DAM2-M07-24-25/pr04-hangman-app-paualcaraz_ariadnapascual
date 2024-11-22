package com.example.navegacio

sealed class Routes(val route: String) {
    // Definim la ruta per accedir a la primera pantalla sense paràmetres
    object SCREEN1 : Routes("SCREEN1")

    // Definim la ruta per accedir a la segona pantalla amb el paràmetre 'nom'
    object SCREEN2 : Routes("SCREEN2/{nom}") {
        fun createRoute(nom: String) = "SCREEN2/$nom"
    }

    // Definim la ruta per accedir a la tercera pantalla amb el paràmetres 'nom', 'salutacio', 'edat'
    object SCREEN3 : Routes("SCREEN3/{nom}/{salutacio}/{edat}") {
        fun createRoute(nom: String, salutacio: String, edat: Int) = "SCREEN3/$nom/$salutacio/$edat"
    }

}