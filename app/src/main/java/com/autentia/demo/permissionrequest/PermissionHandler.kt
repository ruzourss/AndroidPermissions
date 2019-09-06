package com.autentia.demo.permissionrequest

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PermissionHandler {

    enum class Permission(val permission: String, val explanation: String) {
        CAMERA(
            Manifest.permission.CAMERA,
            "Para poder abrir la camera se necesitan permisos"
        ),
        CALENDAR(
            Manifest.permission.WRITE_CALENDAR,
            "Para poder abrir el calendario se necesitan permisos"
        )
    }

    companion object {
        fun checkPermission(
            activity: AppCompatActivity,
            permission: Permission,
            permissionRequestId: Int
        ) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    permission.permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        permission.permission
                    )
                ) {
                    buildDialog(activity, permission, permissionRequestId).show()
                } else {
                    activity.requestPermission(
                        arrayOf(permission.permission),
                        permissionRequestId
                    )
                }
            }
        }

        private fun buildDialog(
            activity: AppCompatActivity,
            permission: Permission,
            permissionRequestId: Int
        ): MaterialAlertDialogBuilder {
            return MaterialAlertDialogBuilder(activity)
                .setMessage(permission.explanation)
                .setPositiveButton("OK") { _, _ ->
                    activity.requestPermission(
                        arrayOf(permission.permission),
                        permissionRequestId
                    )
                }
                .setNegativeButton("No") { _, _ -> }
        }
    }

}
