package soy.gabimoreno.imagegenerator.presentation

import android.graphics.Bitmap.CompressFormat
import android.graphics.Color
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.core.view.drawToBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import soy.gabimoreno.imagegenerator.domain.BRIGHTNESS
import soy.gabimoreno.imagegenerator.domain.Calculator
import soy.gabimoreno.imagegenerator.domain.SATURATION
import soy.gabimoreno.imagegenerator.domain.mapNumber
import java.io.File
import java.io.FileOutputStream

class MainViewModel : ViewModel() {

    companion object {
        val saturation = SATURATION.toFloat().mapNumber(0f, 255f, 0f, 1f)
        val brightness = BRIGHTNESS.toFloat().mapNumber(0f, 255f, 0f, 1f)
    }

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private val _calculator = MutableLiveData<Calculator>()
    val calculator: LiveData<Calculator> = _calculator

    fun exportPNG(view: View, text: String) {
        val bitmap = view.drawToBitmap()
        val applicationContext = view.context.applicationContext
        val dir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val pathname = "$dir/image"
        val file = File(pathname)
        val fos = FileOutputStream(pathname)
        bitmap.compress(CompressFormat.PNG, 100, fos)
        fos.close()

        MediaStore.Images.Media.insertImage(
            applicationContext.contentResolver,
            file.absolutePath,
            file.name,
            file.name
        )

        showOutPutParameters(text)
        changeBackground(text)
    }

    private fun showOutPutParameters(text: String) {
        val calculator = Calculator(text)
        val nSides = calculator.getNumberOfSides()
        val firstGradientColor = calculator.getFirstGradientColor()
        val secondGradientColor = calculator.getSecondGradientColor()

        val firstGradientColorRGB = Color.HSVToColor(floatArrayOf(firstGradientColor.toFloat(), saturation, brightness))
        val secondGradientColorRGB = Color.HSVToColor(floatArrayOf(secondGradientColor.toFloat(), saturation, brightness))

        _text.value = "nSides: $nSides, firstColor: $firstGradientColorRGB, secondColor: $secondGradientColorRGB"
    }

    private fun changeBackground(text: String) {
        val calculator = Calculator(text)
        _calculator.value = calculator
    }
}
