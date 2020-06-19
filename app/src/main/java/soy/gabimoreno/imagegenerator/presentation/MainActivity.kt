package soy.gabimoreno.imagegenerator.presentation

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import soy.gabimoreno.imagegenerator.R
import soy.gabimoreno.imagegenerator.domain.CANVAS_HEIGHT
import soy.gabimoreno.imagegenerator.domain.CANVAS_WIDTH
import soy.gabimoreno.imagegenerator.framework.SearchManager
import soy.gabimoreno.imagegenerator.framework.requestPermission
import soy.gabimoreno.imagegenerator.framework.setHeightParam
import soy.gabimoreno.imagegenerator.framework.setWidthParam
import soy.gabimoreno.imagegenerator.presentation.customview.Polygon

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermission(this)
        initImages()
        initText()
        initEditText()
        initButton()
    }

    private fun initImages() {
        viewModel.gradientDrawable.observe(
            this,
            Observer { gradientDrawable ->
                ivBackground.background = gradientDrawable
            }
        )

        viewModel.nSides.observe(
            this,
            Observer { nSides ->
                ivPolygon.background = Polygon(nSides)
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
                    viewModel.showImageAndParameters(input)
                }
            })
    }

    private fun initButton() {
        btn.setOnClickListener {
            getBitmapFromLayoutPerform { bitmap ->
                viewModel.exportPNG(bitmap)
            }
        }
    }

    private fun getBitmapFromLayoutPerform(callback: (Bitmap) -> Unit) {
        val width = clOutput.width
        val height = clOutput.height
        clOutput.setWidthParam(CANVAS_WIDTH)
        clOutput.setHeightParam(CANVAS_HEIGHT)
        Handler().postDelayed({
            val bitmap = clOutput.drawToBitmap()
            callback(bitmap)
            clOutput.setWidthParam(width)
            clOutput.setHeightParam(height)
        }, 50)
    }
}
