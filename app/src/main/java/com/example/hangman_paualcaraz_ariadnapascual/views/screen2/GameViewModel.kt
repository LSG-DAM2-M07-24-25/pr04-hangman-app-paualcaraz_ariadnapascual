import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    // Lista de palabras posibles
    private val wordList = listOf("AHORCADO", "ANDROID", "KOTLIN", "COMPOSE", "PROGRAMAR")

    // Palabra secreta seleccionada aleatoriamente
    private val secretWord = wordList.random()

    val displayedWord = mutableStateOf("_ ".repeat(secretWord.length).trim())
    val selectedLetters = mutableStateOf(setOf<Char>())
    val attemptsLeft = mutableStateOf(6)
    val hintsLeft = mutableStateOf(3) // Número de pistas disponibles

    // Actualiza el estado del juego al seleccionar una letra
    fun onLetterSelected(letter: Char) {
        if (letter in selectedLetters.value) return // Evita seleccionar la misma letra dos veces

        selectedLetters.value = selectedLetters.value + letter

        if (letter in secretWord) {
            // Si la letra está en la palabra, actualiza el progreso
            updateDisplayedWord()
        } else {
            // Si no está, reduce los intentos
            attemptsLeft.value = attemptsLeft.value - 1
        }
    }

    private fun updateDisplayedWord() {
        displayedWord.value = secretWord.map {
            if (it in selectedLetters.value) it else '_'
        }.joinToString(" ")
    }

    // Comprueba si el jugador ha ganado
    fun hasWon() = !displayedWord.value.contains('_')

    // Comprueba si el jugador ha perdido
    fun hasLost() = attemptsLeft.value <= 0

    // Comprueba si la palabra completa es correcta
    fun checkFullWord(word: String) {
        if (word.equals(secretWord, ignoreCase = true)) {
            displayedWord.value = secretWord // Si es correcta, muestra la palabra completa
        } else {
            attemptsLeft.value = attemptsLeft.value - 1 // Reduce intentos si es incorrecta
        }
    }

    // Usa una pista para revelar una letra
    fun useHint() {
        if (hintsLeft.value > 0) {
            // Encuentra una letra no descubierta
            val undiscoveredLetters = secretWord.filter { it !in selectedLetters.value }
            if (undiscoveredLetters.isNotEmpty()) {
                val hintLetter = undiscoveredLetters.random()
                selectedLetters.value = selectedLetters.value + hintLetter
                updateDisplayedWord()
                hintsLeft.value = hintsLeft.value - 1 // Reduce las pistas disponibles
            }
        }
    }
}
