package soy.gabimoreno.imagegenerator.domain

import android.graphics.Color

class HueToRGBConverter(private val hue: Int) {

    companion object {
        private val saturation = SATURATION.toFloat().mapNumber(0f, 255f, 0f, 1f)
        private val brightness = BRIGHTNESS.toFloat().mapNumber(0f, 255f, 0f, 1f)
    }

    fun get(): Int {
        println("h: $hue, s: $saturation, b: $brightness")
        return Color.HSVToColor(floatArrayOf(hue.toFloat(), saturation, brightness))
    }
}
