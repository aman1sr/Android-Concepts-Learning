package com.petofy.androidarchdemoprojects.arcore

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.ar.core.codelabs.hellogeospatial.helpers.GeoPermissionsHelper
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityArcoreBinding
import com.petofy.androidarchdemoprojects.helper.CameraPermissionHelper

class ARCoreActivity : AppCompatActivity() {

    companion object{
        val TAG = "ARCoreActivity_d"
    }
    lateinit var binding: ActivityArcoreBinding
    lateinit var  fusedLocationClient : FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArcoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        checkCameraLocationPermission()

        binding.btnGetLastLocation.setOnClickListener {
            getLastLocation()
        }
    }

    private fun checkCameraLocationPermission() {
        if (!GeoPermissionsHelper.hasGeoPermissions(this)) {
            GeoPermissionsHelper.requestPermissions(this);
            return;
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            var latitude = 0.0
            var longitude = 0.0

            location?.let {
                latitude = it.latitude
                longitude = it.longitude
                checkVPsAvailabitlity(latitude,longitude)
            }
            if (location == null) {
                Log.e(TAG, "getLastLocation: NULL!!!", )
            }

        }
    }

    private fun checkVPsAvailabitlity(latitude: Double, longitude: Double) {
        Log.d(TAG, "location: $latitude , $longitude")

    }
}