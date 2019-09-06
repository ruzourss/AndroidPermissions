package com.autentia.demo.permissionrequest

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.autentia.demo.permissionrequest.PermissionHandler.Permission.CALENDAR
import com.autentia.demo.permissionrequest.PermissionHandler.Permission.CAMERA
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val MY_PERMISSIONS_REQUEST_CAMERA = 1000
        const val MY_PERMISSIONS_REQUEST_CALENDAR = 2000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        request_camera_permission_button.setOnClickListener {
            PermissionHandler.checkPermission(this, CAMERA, MY_PERMISSIONS_REQUEST_CAMERA)
        }

        request_calendar_permission_button.setOnClickListener {
            PermissionHandler.checkPermission(this, CALENDAR, MY_PERMISSIONS_REQUEST_CALENDAR)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_CAMERA -> {
                if (hasPermission(grantResults)) {
                    Log.d(MainActivity::class.java.name, "Permission camera was granted")
                }
            }
            MY_PERMISSIONS_REQUEST_CALENDAR -> {
                if (hasPermission(grantResults)) {
                    Log.d(MainActivity::class.java.name, "Permission calendar was granted")
                }
            }
        }
    }

    private fun hasPermission(grantResults: IntArray) =
        grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED

}
