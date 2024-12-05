package com.example.hangman_paualcaraz_ariadnapascual.views.ResultScreen


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hangman_paualcaraz_ariadnapascual.views.Routes


@Composable
fun WinnerScreen(navController: NavController, attempts: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Congratulations!", fontSize = 36.sp)
        Text("You have succeeded after $attempts attempts", fontSize = 18.sp, modifier = Modifier.padding(top = 16.dp))

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para volver a jugar
        Button(onClick = { navController.navigate(Routes.GAME_SCREEN) }) {
            Text("Play again")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para volver al menú (redirige a Screen1)
        Button(onClick = { navController.navigate(Routes.SCREEN1) }) {
            Text("Menu")
        }
    }
}

