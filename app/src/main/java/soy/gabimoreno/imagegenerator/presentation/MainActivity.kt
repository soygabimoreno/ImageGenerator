package soy.gabimoreno.imagegenerator.presentation

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import soy.gabimoreno.imagegenerator.R
import soy.gabimoreno.imagegenerator.domain.HueToRGBConverter
import soy.gabimoreno.imagegenerator.framework.SearchManager
import soy.gabimoreno.imagegenerator.framework.requestPermission

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermission(this)
        initImage()
        initEditText()
        initText()
    }

    private fun initImage() {
        viewModel.calculator.observe(
            this,
            Observer { calculator ->
                val firstHue = calculator.getFirstGradientHue()
                val secondHue = calculator.getSecondGradientHue()
                val outputColor1 = HueToRGBConverter(firstHue).get()
                val outputColor2 = HueToRGBConverter(secondHue).get()
                val firstColor = outputColor1.hueToColor()
                val secondColor = outputColor2.hueToColor()

                val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(firstColor, secondColor))
                iv.background = gradientDrawable
            }
        )
    }

    private fun Int.hueToColor(): Int {
        val hexStringColor = Integer.toHexString(this)

        val r = hexStringColor.subSequence(2, 3).toString()
        val g = hexStringColor.subSequence(4, 5).toString()
        val b = hexStringColor.subSequence(6, 7).toString()

        val rDec = Integer.parseInt(r, 16)
        val gDec = Integer.parseInt(g, 16)
        val bDec = Integer.parseInt(b, 16)

        return Color.argb(255, rDec, gDec, bDec)
    }

    private fun initText() {
        viewModel.text.observe(
            this,
            Observer { text ->
                tv.text = text
            }
        )
    }

    private fun initEditText() {
        SearchManager(
            et,
            object : SearchManager.Listener {
                override fun onComplete(input: String) {
                    exportPNG()
                }
            })
    }

    private fun exportPNG() {
        val text = et.text.toString()
        viewModel.exportPNG(iv, text)
    }
}
