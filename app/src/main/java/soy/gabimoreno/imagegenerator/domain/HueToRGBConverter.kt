package soy.gabimoreno.imagegenerator.domain

import android.graphics.Color

class HueToRGBConverter(private val hue: Int) {

    companion object {
        private val saturation = SATURATION.toFloat().mapNumber(0f, 100f, 0f, 1f)
        private val brightness = BRIGHTNESS.toFloat().mapNumber(0f, 100f, 0f, 1f)
    }

    fun get(): Int {
        println("h: $hue, s: $saturation, b: $brightness")
        val color = Color.HSVToColor(floatArrayOf(hue.toFloat(), saturation, brightness))
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb(255, red, green, blue)
    }

    fun getHexString(): String {
        val rgb = get()
        val raw = Integer.toHexString(rgb)
        return "#${raw.substring(2)}"
    }
}
