package com.example.navigation

sealed class Routes(val route: String){
    object MenuScreen : Routes ("MenuScreen")
    object GameScreen : Routes ("GameScreen/{dificultadSeleccionada}"){
        fun createRoute(dificultadSeleccionada: String) = "GameScreen/$dificultadSeleccionada"
    }
}

object ResultScreen : Routes("ResultScreen/{dificultadSeleccionada}/{estado}/{intentosConsumidos}/{palabraSecreta}"){
    fun createRoute(diificultadSeleccionada: String, estado: String, intentosConsumidos:Int, palabraSecreta: String) =
        "ResultScreen /$dificultadSeleccionada/$estado/$intentosConsumidos/$palabraSecreta"
}