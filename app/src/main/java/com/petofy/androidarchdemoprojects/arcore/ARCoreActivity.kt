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
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityArcoreBinding

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

        binding.btnGetLastLocation.setOnClickListener {
            getLastLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {

        fusedLocationClient.lastLocation.addOnSuccessListener { location -> // todo; grant the location by def
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
        Log.d(TAG, "checkVPsAvailabitlity: ")

    }
}