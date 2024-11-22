package com.example.hangman_paualcaraz_ariadnapascual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hangman_paualcaraz_ariadnapascual.ui.theme.Hangman_PauAlcaraz_AriadnaPascualTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Cambia al tema principal después de la Launch Screen
        setTheme(R.style.Theme_MyApp)

        super.onCreate(savedInstanceState)

        enableEdgeToEdge() // Si esto es necesario para tu diseño
        setContent {
            Hangman_PauAlcaraz_AriadnaPascualTheme { // Usa tu tema principal en Compose
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Hangman_PauAlcaraz_AriadnaPascualTheme {
        Greeting("Android")
    }
}