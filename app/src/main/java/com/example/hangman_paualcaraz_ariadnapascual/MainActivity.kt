package com.example.navegacio.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.navegacio.Routes

/**
 * Definició de la pantalla 1
 * @param navController
 * @author RIS
 * @since 2024-11-20
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen1(navController: NavController) {
    var nom: String by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {

        TextField(
            value = nom,
            placeholder = { Text("Nom") }, // Per tal de que es mostrin les instruccions del TextField
            onValueChange = { nom = it }
        )

        Button(
            onClick = { navController.navigate(Routes.SCREEN2.createRoute(nom)) },
            enabled = if (nom.isNotEmpty()) true else false
        ) {
            Text("Següent pas")
        }
    }
}