package com.petofy.androidarchdemoprojects.permission

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.ar.core.codelabs.hellogeospatial.helpers.GeoPermissionsHelper
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityPermissionBinding
import com.petofy.androidarchdemoprojects.helper.CameraPermissionHelper
import android.provider.Settings;
import androidx.activity.result.contract.ActivityResultContracts
/*
* https://developer.android.com/training/data-storage/manage-all-files
* https://medium.com/@kezzieleo/manage-external-storage-permission-android-studio-java-9c3554cf79a7
*
*
* todo: visit  (https://developer.android.com/training/data-storage/shared/documents-files)
* */
class PermissionActivity : AppCompatActivity() {

    lateinit var binding: ActivityPermissionBinding
    companion object{
        val TAG = "PermissionActivity_d"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnExternalStorage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 33 && !Environment.isExternalStorageManager()) {
                Log.d(TAG, "1.Environment.isExternalStorageManager(): "+ Environment.isExternalStorageManager());
                Log.d(TAG, "1.API 33: ");
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE), 101)
                requestManageExternalStoragePermission()
            }
        }

        binding.btnSingle.setOnClickListener {
            Toast.makeText(this, "Camera Request", Toast.LENGTH_SHORT).show()
            if (!CameraPermissionHelper.hasCameraPermission(this)) {
                CameraPermissionHelper.requestCameraPermission(this);
            }
        }
        binding.btnDouble.setOnClickListener {
            Toast.makeText(this, "Location & Camera  Request", Toast.LENGTH_SHORT).show()
            if (!GeoPermissionsHelper.hasGeoPermissions(this)) {
                GeoPermissionsHelper.requestPermissions(this);
            }
        }

    }

    private fun requestManageExternalStoragePermission() {
        val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        storageActivityResultLauncher.launch(intent)
    }

   private val storageActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
           if (Environment.isExternalStorageManager()) {
               // Manage External Storage Permissions Granted
               Log.d(TAG, "onActivityResult: Manage External Storage Permissions Granted")
           } else {
               Toast.makeText(this, "Storage Permissions Denied", Toast.LENGTH_SHORT).show()
           }
       }
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