package com.example.hangman_paualcaraz_ariadnapascual.views.screen1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hangman_paualcaraz_ariadnapascual.R
import com.example.hangman_paualcaraz_ariadnapascual.views.Routes

@Composable
fun Screen1(navController: NavController) {
    val showHelpDialog = remember { mutableStateOf(false) }
    val showDifficultyWarning = remember { mutableStateOf(false) }
    val selectedDifficulty = remember { mutableStateOf("Difficulty") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "Fondo del juego",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Contenido sobre la imagen de fondo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón de selección de dificultad con tamaño fijo
            MyDropDownMenu(selectedDifficulty)
            Spacer(modifier = Modifier.height(24.dp))

            // Fila con botones Play y Help
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        if (selectedDifficulty.value == "Difficulty") {
                            showDifficultyWarning.value = true
                        } else {
                            navController.navigate(Routes.GAME_SCREEN)
                        }
                    },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Blue,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Play", fontSize = 18.sp)
                }

                Button(
                    onClick = { showHelpDialog.value = true },
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Blue,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Help", fontSize = 18.sp)
                }
            }
        }
    }

    // Diálogo de ayuda
    if (showHelpDialog.value) {
        AlertDialog(
            onDismissRequest = { showHelpDialog.value = false },
            title = { Text(text = "Normas Básicas del Juego", fontSize = 20.sp, color = Color.Blue) },
            text = {
                Column {
                    Text("1. Adivina la palabra antes de que se complete el ahorcado.", fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("2. Selecciona una letra en cada turno.", fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("3. Cada letra incorrecta añadirá una parte al dibujo del ahorcado.", fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("4. Ganas si adivinas la palabra antes de que el dibujo se complete.", fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("5. ¡Buena suerte y diviértete!", fontSize = 16.sp)
                }
            },
            confirmButton = {
                Button(
                    onClick = { showHelpDialog.value = false },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Blue,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "OK")
                }
            }
        )
    }

    // Diálogo de advertencia para la selección de dificultad
    if (showDifficultyWarning.value) {
        AlertDialog(
            onDismissRequest = { showDifficultyWarning.value = false },
            title = { Text(text = "¡Advertencia!", fontSize = 20.sp, color = Color.Red) },
            text = { Text("Por favor, selecciona una dificultad antes de continuar.", fontSize = 16.sp) },
            confirmButton = {
                Button(
                    onClick = { showDifficultyWarning.value = false },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Blue,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "OK")
                }
            }
        )
    }
}

@Composable
fun MyDropDownMenu(selectedDifficulty: MutableState<String>) {
    val expanded = remember { mutableStateOf(false) }
    val difficulties = listOf("Fácil", "Media", "Difícil")

    Button(
        onClick = { expanded.value = true },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp), // Tamaño fijo igual a los otros botones
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Blue,
            contentColor = Color.White
        )
    ) {
        Text(text = selectedDifficulty.value, fontSize = 16.sp)
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false }
    ) {
        difficulties.forEach { difficulty ->
            DropdownMenuItem(onClick = {
                selectedDifficulty.value = difficulty
                expanded.value = false
            }) {
                Text(text = difficulty, fontSize = 14.sp, color = Color.Black)
            }
        }
    }
}
