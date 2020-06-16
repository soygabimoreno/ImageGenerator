package soy.gabimoreno.imagegenerator.domain

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HueToRGBConverterTest {

    @Test
    fun if_hue_is_108_then_the_RGB_is_0xFF239845() {
        val hue = 108
        val rgb = HueToRGBConverter(hue).get()
        assertEquals(-12694981, rgb)
    }
}
