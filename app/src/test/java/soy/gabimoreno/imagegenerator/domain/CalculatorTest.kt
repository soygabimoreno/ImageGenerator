package soy.gabimoreno.imagegenerator.domain

import org.junit.Assert.*
import org.junit.Test

@Suppress("NonAsciiCharacters")
class CalculatorTest {

    @Test
    fun `if input is "Parálisis por Análisis", then the polygon has 18 sides`() {
        val input = "Parálisis por Análisis"
        val calculator = Calculator(input)
        val output = calculator.getNumberOfSides()
        assertEquals(18, output)
    }

    @Test
    fun `if input is "Parálisis por Análisis", then the first hue of the gradient is 208`() {
        val input = "Parálisis por Análisis"
        val calculator = Calculator(input)
        val output = calculator.getFirstGradientColor()
        assertEquals(208, output)
    }

    @Test
    fun `if input is "Parálisis por Análisis", then the second hue of the gradient is 249`() {
        val input = "Parálisis por Análisis"
        val calculator = Calculator(input)
        val output = calculator.getSecondGradientColor()
        assertEquals(249, output)
    }

    @Test
    fun `if input is "Ownership & Accountability", then the second hue of the gradient is 55`() {
        val input = "Ownership & Accountability"
        val calculator = Calculator(input)
        val output = calculator.getSecondGradientColor()
        assertEquals(55, output)
    }

    @Test
    fun `if input is "Manolo" firstWordLength returns 6`() {
        val input = "Manolo"
        val calculator = Calculator(input)
        val length = calculator.getFirstWordLength()
        assertEquals(6, length)
    }

    @Test
    fun `if input is "Manolo mola" firstWordLength returns 6`() {
        val input = "Manolo mola"
        val calculator = Calculator(input)
        val length = calculator.getFirstWordLength()
        assertEquals(6, length)
    }

    @Test
    fun `if input is "Hola" second letter isSecondLetterMajorThanFirstOne returns true`() {
        val input = "Hola"
        val calculator = Calculator(input)
        val secondLetterMajorThanFirstOne = calculator.isSecondLetterMajorThanFirstOne()
        assertTrue(secondLetterMajorThanFirstOne)
    }

    @Test
    fun `if input is "Zapato" second letter isSecondLetterMajorThanFirstOne returns false`() {
        val input = "Zapato"
        val calculator = Calculator(input)
        val secondLetterMajorThanFirstOne = calculator.isSecondLetterMajorThanFirstOne()
        assertFalse(secondLetterMajorThanFirstOne)
    }
}