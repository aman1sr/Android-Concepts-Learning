package com.petofy.androidarchdemoprojects.permission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.ar.core.codelabs.hellogeospatial.helpers.GeoPermissionsHelper
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.helper.CameraPermissionHelper

class PermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        val intent = intent.getStringExtra("type")

        intent?.let {
            when (it.toString()) {
                "single" -> {
                    Toast.makeText(this, "Single Permission", Toast.LENGTH_SHORT).show()
                    if (!CameraPermissionHelper.hasCameraPermission(this)) {
                        CameraPermissionHelper.requestCameraPermission(this);
                        return;
                    }
                }
                "double"-> {
                    Toast.makeText(this, "double Permission", Toast.LENGTH_SHORT).show()
                    if (!GeoPermissionsHelper.hasGeoPermissions(this)) {
                        GeoPermissionsHelper.requestPermissions(this);
                        return;
                    }

                }
            else ->  Toast.makeText(this, "Invalid stuff..", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun checkSinglePermission() {

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (!CameraPermissionHelper.hasCameraPermission(this)) {
            // Use toast instead of snackbar here since the activity will exit.
            Toast.makeText(
                this,
                "Camera permission is needed to run this application",
                Toast.LENGTH_LONG
            )
                .show();
            if (!CameraPermissionHelper.shouldShowRequestPermissionRationale(this)) {
                // Permission denied with checking "Do not ask again".
                CameraPermissionHelper.launchPermissionSettings(this);
            }
            finish();
        }
    }
}