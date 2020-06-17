package soy.gabimoreno.imagegenerator.presentation

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
                val firstRGB = HueToRGBConverter(firstHue).get()
                val secondRGB = HueToRGBConverter(secondHue).get()
                val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.BL_TR, intArrayOf(firstRGB, secondRGB))
                iv.background = gradientDrawable
            }
        )
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
