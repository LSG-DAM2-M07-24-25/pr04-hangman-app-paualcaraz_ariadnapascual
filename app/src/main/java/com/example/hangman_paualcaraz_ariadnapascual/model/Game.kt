package com.example.hangman_paualcaraz_ariadnapascual.model

class Game(private val wordToGuess: String) {
    private val correctGuesses = mutableSetOf<Char>()
    private val incorrectGuesses = mutableSetOf<Char>()
    var remainingAttempts: Int = 6
        private set

    fun guessLetter(letter: Char): Boolean {
        if (letter in wordToGuess) {
            correctGuesses.add(letter)
            return true
        } else {
            incorrectGuesses.add(letter)
            remainingAttempts--
            return false
        }
    }

    fun getMaskedWord(): String {
        return wordToGuess.map { if (it in correctGuesses) it else '_' }.joinToString(" ")
    }

    fun isGameWon(): Boolean = wordToGuess.all { it in correctGuesses }

    fun isGameOver(): Boolean = remainingAttempts <= 0
}
