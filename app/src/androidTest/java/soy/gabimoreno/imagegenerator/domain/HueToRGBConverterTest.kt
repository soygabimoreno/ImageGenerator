package soy.gabimoreno.imagegenerator.domain

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HueToRGBConverterTest {

    @Test
    fun if_hue_is_83_then_the_RGB_color_is_minus_9323170() {
        val hue = 108
        val rgb = HueToRGBConverter(hue).get()
        assertEquals(-9323170, rgb)
    }

    @Test
    fun if_hue_is_83_then_the_RGB_hex_string_color_is_() {
        val hue = 108
        val rgbHexString = HueToRGBConverter(hue).getHexString()
        assertEquals("#71bd5e", rgbHexString)
    }
}
