package com.example.hangman_paualcaraz_ariadnapascual.views.ResultScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoserScreen(navController: NavController, word: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("You Lost!", fontSize = 36.sp)
        Text("The word was \"$word\"", fontSize = 18.sp, modifier = Modifier.padding(top = 16.dp))

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate("game_screen") }) {
            Text("Play again")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("menu_screen") }) {
            Text("Menu")
        }
    }
}
