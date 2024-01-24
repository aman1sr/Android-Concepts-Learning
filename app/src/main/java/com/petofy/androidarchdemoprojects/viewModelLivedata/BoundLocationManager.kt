package com.petofy.androidarchdemoprojects.viewModelLivedata

import android.Manifest
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener

import android.location.LocationManager
import android.location.LocationRequest
import android.util.Log
import androidx.core.app.ActivityCompat

object BoundLocationManager {

    fun bindLocationListenerIn(
        lifecycleOwner: LifecycleOwner,
        listener: LocationListener,
        context: Context
    ) {
        BoundLocationListener(lifecycleOwner, listener, context)
    }

    class BoundLocationListener(
        lifecycleOwner: LifecycleOwner,
        private val listener: LocationListener,
        private val context: Context
    ) : LifecycleObserver {        // todo: implement LifecycleEventObserver
        private var locationManager: LocationManager? = null

        init {
            lifecycleOwner.lifecycle.addObserver(this)
        }

        //        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        //
        //        }

        fun bindLocationListenerIn(lifecycleOwner: LifecycleOwner, listener: LocationListener, context: Context) {
            BoundLocationListener(lifecycleOwner, listener, context)
        }
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun addLocationListener() {
            locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 50f, listener)
            Log.d(HomeVMActivity.TAG, "Listener added")


            // Force an update with the last location, if available.
            val lastLocation = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            lastLocation?.let { listener.onLocationChanged(it) }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun removeLocationListener() {
            locationManager?.removeUpdates(listener)
            locationManager = null
            Log.d(HomeVMActivity.TAG, "Listener removed")
        }
    }

}

