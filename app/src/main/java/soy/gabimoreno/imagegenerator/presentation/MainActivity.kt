package soy.gabimoreno.imagegenerator.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import soy.gabimoreno.imagegenerator.R
import soy.gabimoreno.imagegenerator.framework.requestPermission

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermission(this)

        btn.setOnClickListener {
            exportPNG()
        }
    }

    private fun exportPNG() {
        viewModel.exportPNG(iv)
    }
}
