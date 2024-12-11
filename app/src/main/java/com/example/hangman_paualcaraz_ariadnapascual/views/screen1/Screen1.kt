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
    val selectedDifficulty = remember { mutableStateOf("Select Difficulty") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Imagen de fondo con un filtro de color para crear un contraste
        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Filtro de color oscuro para hacer el contenido más visible
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        )

        // Contenido centrado sobre la imagen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Menú desplegable para seleccionar dificultad
            MyDropDownMenu(selectedDifficulty)

            Spacer(modifier = Modifier.height(24.dp))

            // Fila con los botones Play y Help
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        if (selectedDifficulty.value == "Select Difficulty") {
                            showDifficultyWarning.value = true
                        } else {
                            navController.navigate(Routes.GAME_SCREEN)
                        }
                    },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(4.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2196F3), contentColor = Color.White)
                ) {
                    Text(text = "Play", fontSize = 18.sp, modifier = Modifier.padding(8.dp))
                }

                Button(
                    onClick = { showHelpDialog.value = true },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(4.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2196F3), contentColor = Color.White)
                ) {
                    Text(text = "Help", fontSize = 18.sp, modifier = Modifier.padding(8.dp))
                }
            }
        }
    }

    // Diálogo de ayuda con más estilo
    if (showHelpDialog.value) {
        AlertDialog(
            onDismissRequest = { showHelpDialog.value = false },
            title = {
                Text(text = "Game Instructions", fontSize = 20.sp, color = Color.Blue)
            },
            text = {
                Column {
                    Text("1. Guess the word before the hangman drawing is complete.", fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("2. Select a letter on each turn.", fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("3. Each wrong letter will add a part to the drawing.", fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("4. Win by guessing the word before the drawing finishes.", fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("5. Good luck and have fun!", fontSize = 16.sp)
                }
            },
            confirmButton = {
                Button(
                    onClick = { showHelpDialog.value = false },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2196F3), contentColor = Color.White)
                ) {
                    Text(text = "OK")
                }
            },
            shape = RoundedCornerShape(12.dp)
        )
    }

    // Diálogo de advertencia de dificultad
    if (showDifficultyWarning.value) {
        AlertDialog(
            onDismissRequest = { showDifficultyWarning.value = false },
            title = {
                Text(text = "Warning!", fontSize = 20.sp, color = Color.Red)
            },
            text = { Text("Please select a difficulty level before continuing.", fontSize = 16.sp) },
            confirmButton = {
                Button(
                    onClick = { showDifficultyWarning.value = false },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2196F3), contentColor = Color.White)
                ) {
                    Text(text = "OK")
                }
            },
            shape = RoundedCornerShape(12.dp)
        )
    }
}

@Composable
fun MyDropDownMenu(selectedDifficulty: MutableState<String>) {
    val expanded = remember { mutableStateOf(false) }
    val difficulties = listOf("Easy", "Medium", "Hard")

    Button(
        onClick = { expanded.value = true },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2196F3), contentColor = Color.White)
    ) {
        Text(text = selectedDifficulty.value, fontSize = 16.sp)
    }

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
        modifier = Modifier.background(Color.White, RoundedCornerShape(12.dp))
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
