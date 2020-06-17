package soy.gabimoreno.imagegenerator.domain

import java.text.Normalizer
import java.util.*

private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

class Calculator(private val input: String) {

    fun getNumberOfSides(): Int {
        val alphabet = getAlphabetFromFirstLetter()
        return alphabet.nSides
    }

    fun getFirstGradientHue(): Int {
        val alphabet = getAlphabetFromFirstLetter()
        return alphabet.hue
    }

    fun getSecondGradientHue(): Int {
        val alphabet = getAlphabetFromLetter()
        return alphabet.hue
    }

    private fun getAlphabetFromFirstLetter(): Alphabet {
        val firstLetter = input
            .first()
            .toString()
            .unaccent()
            .toUpperCase(Locale.US)
        val alphabet = Alphabet.valueOf(firstLetter)
        println(alphabet.toString())
        return alphabet
    }

    private fun getAlphabetFromLetter(): Alphabet {
        val secondLetter = input[1]
        val formattedLetter = secondLetter.toString()
            .unaccent()
            .toUpperCase(Locale.US)
        val ordinal = Alphabet.valueOf(formattedLetter).ordinal
        val position = if (isSecondLetterMajorThanFirstOne()) {
            val steps = ordinal + getFirstWordLength() - 1
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
            val steps = getFirstWordLength() - 1 - ordinal
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

    fun getFirstWordLength(): Int {
        val firstWord = input.substringBefore(" ")
        return firstWord.length
    }

    fun isSecondLetterMajorThanFirstOne(): Boolean {
        val firstLetter = input[0].toLowerCase()
        val secondLetter = input[1].toLowerCase()
        return secondLetter - firstLetter > 0
    }

    private fun CharSequence.unaccent(): String {
        val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
        return REGEX_UNACCENT.replace(temp, "")
    }
}
