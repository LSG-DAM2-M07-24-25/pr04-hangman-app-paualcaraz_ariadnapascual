package com.example.hangman_paualcaraz_ariadnapascual.views.screen2

import GameViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun GameScreen(viewModel: GameViewModel = viewModel()) {
    // Estado para capturar la palabra ingresada en el TextField
    var userWord by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") } // Mensaje para el estado del juego

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Palabra oculta
        Text(
            text = viewModel.displayedWord.value,
            fontSize = 32.sp,
            modifier = Modifier.padding(top = 16.dp)
        )

        // Intentos restantes y pistas disponibles
        Text(
            text = "Attempts Left: ${viewModel.attemptsLeft.value} | Hints Left: ${viewModel.hintsLeft.value}",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )

        // Mensaje animado para el estado
        AnimatedMessage(message = message)

        // Animación del ahorcado
        AhorcadoCanvas(errors = 6 - viewModel.attemptsLeft.value)

        // Botón para usar una pista
        Button(
            onClick = { viewModel.useHint() },
            enabled = viewModel.hintsLeft.value > 0,
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Hint", fontSize = 18.sp)
        }

        // Teclado virtual
        val alphabet = ('A'..'Z')
        Column {
            alphabet.chunked(7).forEach { row ->
                Row {
                    row.forEach { letter ->
                        Button(
                            onClick = {
                                viewModel.onLetterSelected(letter)
                                message = if (letter in viewModel.displayedWord.value) {
                                    "¡Bien hecho! La letra está en la palabra."
                                } else {
                                    "¡Letra incorrecta, intenta de nuevo!"
                                }
                            },
                            enabled = !viewModel.selectedLetters.value.contains(letter),
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(text = letter.toString(), fontSize = 14.sp)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // TextField para ingresar la palabra completa
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = userWord,
                onValueChange = { userWord = it },
                label = { Text("Enter the letter or word") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Button(
                onClick = {
                    viewModel.checkFullWord(userWord)
                    message = if (userWord.equals(viewModel.displayedWord.value, ignoreCase = true)) {
                        "¡Ganaste! La palabra era ${viewModel.displayedWord.value}."
                    } else {
                        "Palabra incorrecta. Pierdes un intento."
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Submit")
            }
        }
    }
}

@Composable
fun AnimatedMessage(message: String) {
    if (message.isNotEmpty()) {
        Text(
            text = message,
            fontSize = 18.sp,
            color = Color.Green,
            modifier = Modifier
                .padding(16.dp)
                .background(Color(0xFFE0F7FA)) // Fondo con un color claro
                .padding(8.dp)
        )
    }
}



@Composable
fun AhorcadoCanvas(errors: Int) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth(0.8f) // Ocupa el 80% del ancho disponible
            .aspectRatio(1f) // Mantiene proporción cuadrada
    ) {
        val width = size.width
        val height = size.height

        // Base del poste
        if (errors >= 1) {
            drawLine(
                color = Color.Black,
                start = Offset(0f, height),
                end = Offset(width / 2, height),
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
        }
        // Poste vertical
        if (errors >= 2) {
            drawLine(
                color = Color.Black,
                start = Offset(width / 4, height),
                end = Offset(width / 4, height / 5),
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
        }
        // Poste superior
        if (errors >= 3) {
            drawLine(
                color = Color.Black,
                start = Offset(width / 4, height / 5),
                end = Offset(width * 3 / 4, height / 5),
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
        }
        // Cuerda
        if (errors >= 4) {
            drawLine(
                color = Color.Black,
                start = Offset(width * 3 / 4, height / 5),
                end = Offset(width * 3 / 4, height / 4),
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
        }
        // Cabeza
        if (errors >= 5) {
            drawCircle(
                color = Color.Black,
                center = Offset(width * 3 / 4, height / 4 + height / 20),
                radius = height / 20,
                style = Stroke(width = 8f)
            )
        }
        // Cuerpo
        if (errors >= 6) {
            drawLine(
                color = Color.Black,
                start = Offset(width * 3 / 4, height / 4 + height / 10),
                end = Offset(width * 3 / 4, height / 2),
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
        }
        // Brazo izquierdo
        if (errors >= 7) {
            drawLine(
                color = Color.Black,
                start = Offset(width * 3 / 4, height / 3),
                end = Offset(width * 3 / 4 - width / 10, height / 3 - height / 20),
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
        }
        // Brazo derecho
        if (errors >= 8) {
            drawLine(
                color = Color.Black,
                start = Offset(width * 3 / 4, height / 3),
                end = Offset(width * 3 / 4 + width / 10, height / 3 - height / 20),
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
        }
        // Pierna izquierda
        if (errors >= 9) {
            drawLine(
                color = Color.Black,
                start = Offset(width * 3 / 4, height / 2),
                end = Offset(width * 3 / 4 - width / 15, height * 3 / 4),
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
        }
        // Pierna derecha
        if (errors >= 10) {
            drawLine(
                color = Color.Black,
                start = Offset(width * 3 / 4, height / 2),
                end = Offset(width * 3 / 4 + width / 15, height * 3 / 4),
                strokeWidth = 8f,
                cap = StrokeCap.Round
            )
        }
    }
}