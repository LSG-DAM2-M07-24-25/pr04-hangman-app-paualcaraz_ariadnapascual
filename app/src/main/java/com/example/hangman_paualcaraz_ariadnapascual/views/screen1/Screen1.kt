package com.example.hangman_paualcaraz_ariadnapascual.views.screen1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hangman_paualcaraz_ariadnapascual.R
import com.example.hangman_paualcaraz_ariadnapascual.views.Routes

@Composable
fun Screen1(navController: NavController) {
    // Estado para mostrar o no el diálogo de ayuda
    val showHelpDialog = remember { mutableStateOf(false) }
    val showDifficultyWarning = remember { mutableStateOf(false) } // Estado para el aviso de dificultad

    // Estado para la dificultad seleccionada
    val selectedDifficulty = remember { mutableStateOf("Difficulty") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen del logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo del juego",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(32.dp)) // Espaciado entre logo y menú desplegable

        //Menú desplegable
        MyDropDownMenu(selectedDifficulty)

        Spacer(modifier = Modifier.height(24.dp)) // Espaciado entre dropdown y botones

        //Botón para jugar
        Button(
            onClick = {
                if (selectedDifficulty.value == "Difficulty") {
                    // Si no se seleccionó dificultad, mostrar aviso
                    showDifficultyWarning.value = true
                } else {
                    // Si se seleccionó dificultad, navegar al juego
                    navController.navigate(Routes.GAME_SCREEN)
                }
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text(text = "Play", fontSize = 18.sp)
        }

        // Botón para ayuda
        Button(
            onClick = { showHelpDialog.value = true }, // Abre el diálogo de ayuda
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text(text = "Help", fontSize = 18.sp)
        }
    }

    // Diálogo de ayuda con normas del juego
    if (showHelpDialog.value) {
        AlertDialog(
            onDismissRequest = { showHelpDialog.value = false }, // Cierra el diálogo
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
                    onClick = { showHelpDialog.value = false }, // Cierra el diálogo
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

    // Diálogo de advertencia para dificultad
    if (showDifficultyWarning.value) {
        AlertDialog(
            onDismissRequest = { showDifficultyWarning.value = false }, // Cierra el aviso
            title = { Text(text = "¡Advertencia!", fontSize = 20.sp, color = Color.Red) },
            text = { Text("Por favor, selecciona una dificultad antes de continuar.", fontSize = 16.sp) },
            confirmButton = {
                Button(
                    onClick = { showDifficultyWarning.value = false }, // Cierra el aviso
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

    // Botón que despliega el menú
    Button(
        onClick = { expanded.value = true },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Blue,
            contentColor = Color.White
        )
    ) {
        Text(text = selectedDifficulty.value, fontSize = 16.sp)
    }

    // Menú desplegable
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
