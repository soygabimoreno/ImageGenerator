package soy.gabimoreno.imagegenerator.domain

import java.text.Normalizer
import java.util.*

private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

class CalculateNumberOfSidesFromTextUseCase() {

    operator fun invoke(input: String): Int {
        val firstLetter = input
            .first()
            .toString()
            .unaccent()
            .toUpperCase(Locale.US)
        val polygon = Polygon.valueOf(firstLetter)
        println(polygon.toString())
        return polygon.nSides
    }

//    operator fun invoke(input: String): Int {
//        input.unaccent().forEach { char ->
//            if (char != ' ') {
//                val foo = Polygon.valueOf(char.toString().toUpperCase(Locale.US))
//                println(foo)
//            } else {
//                println("")
//            }
//        }
//        return 0
//    }

    private fun CharSequence.unaccent(): String {
        val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
        return REGEX_UNACCENT.replace(temp, "")
    }
}
