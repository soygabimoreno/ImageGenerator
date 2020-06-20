package soy.gabimoreno.imagegenerator.domain

import java.util.*

class Calculator(private val input: String) {

    fun getNumberOfSides(): Int {
        val alphabet = findAlphabetByPosition(0)
        return alphabet.nSides
    }

    fun getFirstGradientHue(): Int {
        val alphabet = findAlphabetByPosition(1)
        return alphabet.hue
    }

    fun getSecondGradientHue(): Int {
        val alphabet = getAlphabetFromAlgorithm()
        return alphabet.hue
    }

    private fun getAlphabetFromAlgorithm(): Alphabet {
        val ordinal = findAlphabetByPosition(1).ordinal
        val position = if (isThirdLetterMajorThanFirstOne()) {
            val steps = ordinal + getFirstWordLength() - 2
            val alphabetSize = Alphabet.values().size
            var index = steps
            if (index > alphabetSize) {
                index -= alphabetSize
            }
            if (index == alphabetSize) {
                index = 0
            }
            index
        } else {
            val steps = getFirstWordLength() - 2 - ordinal
            val alphabetSize = Alphabet.values().size
            var index = steps
            if (index > 0) {
                index = alphabetSize - index
            }
            if (index < 0) {
                index += alphabetSize
            }
            index
        }
        val alphabet = Alphabet.values()[position]
        println(alphabet.toString())
        return alphabet
    }

    private fun findAlphabetByPosition(position: Int): Alphabet {
        val letter = input[position]
            .toString()
            .toUpperCase(Locale.US)
        return Alphabet.valueOf(letter)
    }

    fun getFirstWordLength(): Int {
        val firstWord = input.substringBefore(" ")
        return firstWord.length
    }

    fun isThirdLetterMajorThanFirstOne(): Boolean {
        val firstLetter = input[1].toLowerCase()
        val secondLetter = input[2].toLowerCase()
        return secondLetter - firstLetter > 0
    }
}
