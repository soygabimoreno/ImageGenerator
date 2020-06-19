package soy.gabimoreno.imagegenerator.presentation

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.drawable.GradientDrawable
import android.os.Environment
import android.view.View
import androidx.core.view.drawToBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import soy.gabimoreno.imagegenerator.domain.CANVAS_HEIGHT
import soy.gabimoreno.imagegenerator.domain.CANVAS_WIDTH
import soy.gabimoreno.imagegenerator.domain.Calculator
import soy.gabimoreno.imagegenerator.domain.HueToRGBConverter
import java.io.File
import java.io.FileOutputStream

class MainViewModel : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val _gradientDrawable = MutableLiveData<GradientDrawable>()
    val gradientDrawable: LiveData<GradientDrawable> = _gradientDrawable

    private val _nSides = MutableLiveData<Int>()
    val nSides: LiveData<Int> = _nSides

    fun exportPNG(view: View) {
        val bitmap = view.drawToBitmap()
        val scaledBitmap = Bitmap.createScaledBitmap(
            bitmap,
            CANVAS_WIDTH,
            CANVAS_HEIGHT,
            true
        )
        val dir = File(Environment.getExternalStorageDirectory().toString() + "/ImageGenerator")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val file = File(dir, "image.png")
        try {
            val fos = FileOutputStream(file)
            scaledBitmap.compress(CompressFormat.PNG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showImageAndParameters(text: String) {
        val calculator = Calculator(text)
        val firstHue = calculator.getFirstGradientHue()
        val secondHue = calculator.getSecondGradientHue()
        val firstRGB = HueToRGBConverter(firstHue).get()
        val secondRGB = HueToRGBConverter(secondHue).get()
        _gradientDrawable.value = GradientDrawable(GradientDrawable.Orientation.BL_TR, intArrayOf(firstRGB, secondRGB))

        val nSides = calculator.getNumberOfSides()
        _nSides.value = nSides

        val firstColor = HueToRGBConverter(firstHue).getHexString()
        val secondColor = HueToRGBConverter(secondHue).getHexString()

        _text.value = "nSides: $nSides, firstColor: $firstColor, secondColor: $secondColor"
    }
}
