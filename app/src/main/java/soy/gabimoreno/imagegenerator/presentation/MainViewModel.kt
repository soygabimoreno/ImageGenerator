package soy.gabimoreno.imagegenerator.presentation

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.drawable.GradientDrawable
import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import soy.gabimoreno.imagegenerator.domain.Calculator
import soy.gabimoreno.imagegenerator.framework.HueToRGBConverter
import java.io.File
import java.io.FileOutputStream

class MainViewModel : ViewModel() {
    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val _gradientDrawable = MutableLiveData<GradientDrawable>()
    val gradientDrawable: LiveData<GradientDrawable> = _gradientDrawable

    private val _nSides = MutableLiveData<Int>()
    val nSides: LiveData<Int> = _nSides

    private lateinit var calculator: Calculator

    fun exportPNG(bitmap: Bitmap) {
        val dir = File("${Environment.getExternalStorageDirectory()}/ImageGenerator")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val file = File(dir, "${calculator.getFirstWord()}.png")
        try {
            val fos = FileOutputStream(file)
            bitmap.compress(CompressFormat.PNG, 100, fos)
            fos.flush()
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showImageAndParameters(text: String) {
        calculator = Calculator(text)
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
