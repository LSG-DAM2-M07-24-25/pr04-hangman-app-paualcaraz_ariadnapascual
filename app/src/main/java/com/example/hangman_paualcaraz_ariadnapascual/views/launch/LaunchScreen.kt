import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
    // Estado para el progreso de la barra
    var progress by remember { mutableStateOf(0f) } // Valor inicial del progreso (0.0 = vacío, 1.0 = lleno)

    // Lanzamos el efecto para manejar el progreso
    LaunchedEffect(Unit) {
        val totalTime = 2000L
        val steps = 20
        val stepDelay = totalTime / steps

        // Actualizamos el progreso gradualmente
        for (i in 1..steps) {
            progress = i / steps.toFloat()
            delay(stepDelay) 
        }

        // Navegamos a la siguiente pantalla
        navController.navigate(Routes.SCREEN1)
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

        Spacer(modifier = Modifier.height(16.dp)) // Separación entre el texto y la barra

        // Mostrar la ProgressBar lineal
        LinearProgressIndicator(
            progress = progress, // Sincronizado con el progreso
            color = Color.Black, // Color de la barra que avanza
            trackColor = Color.LightGray, // Color del fondo
            modifier = Modifier
                .fillMaxWidth(0.8f) // Barra ocupa el 80% del ancho
                .height(8.dp) // Grosor de la barra
        )
    } 
}


