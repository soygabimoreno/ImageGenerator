package soy.gabimoreno.imagegenerator.framework

import android.Manifest
import android.app.Activity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

fun requestPermission(activity: Activity) {
    activity.apply {
        Dexter.withContext(this)
            .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
//                    toast("Permission Granted")
                }

                override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {
                    toast("Permission should be granted")
                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    toast("Permission Denied")
                }
            })
            .check()
    }
}
