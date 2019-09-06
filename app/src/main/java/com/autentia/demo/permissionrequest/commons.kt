package com.autentia.demo.permissionrequest

import android.app.Activity
import androidx.core.app.ActivityCompat

fun Activity.requestPermission(permissionList: Array<String>, requestId: Int) {
    ActivityCompat.requestPermissions(this, permissionList, requestId)
}
