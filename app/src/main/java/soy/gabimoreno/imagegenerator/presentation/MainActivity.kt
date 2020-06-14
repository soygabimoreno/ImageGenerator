package soy.gabimoreno.imagegenerator.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import soy.gabimoreno.imagegenerator.R
import soy.gabimoreno.imagegenerator.domain.BRIGHTNESS
import soy.gabimoreno.imagegenerator.domain.SATURATION
import soy.gabimoreno.imagegenerator.framework.requestPermission

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermission(this)
        initIv()
        initBtn()
        initText()
    }

    private fun initIv() {
        viewModel.color.observe(
            this,
            Observer { color ->
                iv.setBackgroundColor(Color.HSVToColor(floatArrayOf(color.toFloat(), SATURATION.toFloat(), BRIGHTNESS.toFloat())))
            }
        )
    }

    private fun initBtn() {
        btn.setOnClickListener {
            if (et.text.isNotEmpty()) {
                exportPNG()
            }
        }
    }

    private fun initText() {
        viewModel.text.observe(
            this,
            Observer { text ->
                tv.text = text
            }
        )
    }

    private fun exportPNG() {
        val text = et.text.toString()
        viewModel.exportPNG(iv, text)
    }
}
