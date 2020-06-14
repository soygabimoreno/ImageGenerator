package soy.gabimoreno.imagegenerator.domain

import java.text.Normalizer
import java.util.*

private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

class Calculator(private val input: String) {

    fun getNumberOfSides(): Int {
        val polygon = getPolygonFromFirstLetter()
        return polygon.nSides
    }

    fun getFirstGradientColor(): Int {
        val polygon = getPolygonFromFirstLetter()
        return polygon.hue
    }

    fun getSecondGradientColor(): Int {
        val polygon = getPolygonFromLetter()
        return polygon.hue
    }

    private fun getPolygonFromFirstLetter(): Polygon {
        val firstLetter = input
            .first()
            .toString()
            .unaccent()
            .toUpperCase(Locale.US)
        val polygon = Polygon.valueOf(firstLetter)
        println(polygon.toString())
        return polygon
    }

    private fun getPolygonFromLetter(): Polygon {
        val secondLetter = input[1]
        val formattedLetter = secondLetter.toString()
            .unaccent()
            .toUpperCase(Locale.US)
        val ordinal = Polygon.valueOf(formattedLetter).ordinal
        val position = if (isSecondLetterMajorThanFirstOne()) {
            val steps = ordinal + getFirstWordLength() - 1
            val polygonSize = Polygon.values().size
            var index = steps
            if (index > polygonSize) {
                index -= polygonSize
            }
            index
        } else {
            val steps = getFirstWordLength() - 1 - ordinal
            val polygonSize = Polygon.values().size
            var index = steps
            if (index > 0) {
                index = polygonSize - index
            }
            index
        }
        val polygon = Polygon.values()[position]
        println(polygon.toString())
        return polygon
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
