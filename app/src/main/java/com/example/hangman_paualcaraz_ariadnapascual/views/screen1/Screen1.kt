package com.example.hangman_paualcaraz_ariadnapascual.views.screen1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

@Composable
fun Screen1(navController: NavController) {
    // Estado para mostrar o no el diálogo de ayuda
    val showHelpDialog = remember { mutableStateOf(false) }

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
            painter = painterResource(id = R.drawable.logo), // Reemplaza con tu logo
            contentDescription = "Logo del juego",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(32.dp)) // Espaciado entre logo y menú desplegable

        // Menú desplegable
        MyDropDownMenu()

        Spacer(modifier = Modifier.height(24.dp)) // Espaciado entre dropdown y botones

        // Botón para jugar
        Button(
            onClick = { /* Navegación al juego */ },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(vertical = 8.dp),
            colors = androidx.compose.material.ButtonDefaults.buttonColors(
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
            colors = androidx.compose.material.ButtonDefaults.buttonColors(
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
                    colors = androidx.compose.material.ButtonDefaults.buttonColors(
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
fun MyDropDownMenu() {
    val selectedText = remember { mutableStateOf("Difficulty") } // Sin delegados
    val expanded = remember { mutableStateOf(false) } // Sin delegados
    val difficulties = listOf("Fácil", "Media", "Difícil")

    // Botón que despliega el menú
    Button(
        onClick = { expanded.value = true }, // Cambia el estado usando .value
        shape = RoundedCornerShape(8.dp),
        colors = androidx.compose.material.ButtonDefaults.buttonColors(
            backgroundColor = Color.Blue,
            contentColor = Color.White
        )
    ) {
        Text(text = selectedText.value, fontSize = 16.sp) // Accede al valor con .value
    }

    // Menú desplegable
    DropdownMenu(
        expanded = expanded.value, // Usa .value aquí también
        onDismissRequest = { expanded.value = false }
    ) {
        difficulties.forEach { difficulty ->
            DropdownMenuItem(onClick = {
                selectedText.value = difficulty // Actualiza el texto seleccionado
                expanded.value = false
            }) {
                Text(text = difficulty, fontSize = 14.sp, color = Color.Black)
            }
        }
    }
}
