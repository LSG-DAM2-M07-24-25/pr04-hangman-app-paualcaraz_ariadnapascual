package com.example.hangman_paualcaraz_ariadnapascual.views.launch

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hangman_paualcaraz_ariadnapascual.R
import com.example.hangman_paualcaraz_ariadnapascual.views.Routes
import kotlinx.coroutines.delay

@Composable
fun LaunchScreen(navController: NavController) {
    // Navegar automáticamente después de un retraso
    LaunchedEffect(Unit) {
        delay(2000) // Espera 2 segundos
        navController.navigate(Routes.SCREEN1) // Navega a Screen1
    }

    // Diseño de la pantalla Splash
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Mostrar el logo
        Image(
            painter = painterResource(id = R.drawable.logo), // Referencia al logo en drawable
            contentDescription = "Logo del juego",
            modifier = Modifier.size(150.dp) // Tamaño del logo
        )

        // Mostrar texto del nombre del juego
        Text(
            text = "Hangman Game",
            fontSize = 24.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}
