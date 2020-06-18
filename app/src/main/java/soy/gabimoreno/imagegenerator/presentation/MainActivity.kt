package soy.gabimoreno.imagegenerator.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import soy.gabimoreno.imagegenerator.R
import soy.gabimoreno.imagegenerator.framework.SearchManager
import soy.gabimoreno.imagegenerator.framework.requestPermission
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
            viewModel.exportPNG(clOutput)
        }
    }
}
