package soy.gabimoreno.imagegenerator.domain

import org.junit.Assert.assertEquals
import org.junit.Test

@Suppress("NonAsciiCharacters")
class CalculateNumberOfSidesFromTextUseCaseTest {

    @Test
    fun `if input is "Parálisis por Análisis", then the output is a polygon with 18 sides`() {
        val input = "Parálisis por Análisis"

        val calculateNumberOfSidesFromTextUseCase = CalculateNumberOfSidesFromTextUseCase()
        val output = calculateNumberOfSidesFromTextUseCase(input)
        assertEquals(18, output)
    }

//    @Test
//    fun `if input is "Parálisis por Análisis", then the output is a gradient background from 123 to 456`() {
//        assertEquals(4, 2 + 2)
//    }
}
