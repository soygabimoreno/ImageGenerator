package soy.gabimoreno.imagegenerator.presentation

import android.graphics.Bitmap.CompressFormat
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.core.view.drawToBitmap
import androidx.lifecycle.ViewModel
import java.io.File
import java.io.FileOutputStream

class MainViewModel : ViewModel() {

    fun exportPNG(view: View) {
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
    }
}
