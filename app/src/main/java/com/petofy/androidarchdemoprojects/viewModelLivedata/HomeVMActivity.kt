package com.petofy.androidarchdemoprojects.viewModelLivedata

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.location.LocationListener;
import com.petofy.androidarchdemoprojects.databinding.ActivityHomeVmactivityBinding
import com.petofy.androidarchdemoprojects.viewModelLivedata.custom.TestCustomVM

//todo:  viewModel (https://developer.android.com/topic/libraries/architecture/viewmodel)
//       --ll--- doc2 (https://developer.android.com/reference/androidx/lifecycle/ViewModel)

// todo: LiveData (https://developer.android.com/topic/libraries/architecture/livedata)
// SavedStateHandler : (https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-savedstate)

class HomeVMActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeVmactivityBinding
    val locationPermissionList = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION)

    /*
    * include the androidx.activity:activity-ktx:$Version dependency (AndroidX dependency) >> viewModels()
    * you can make use of property delegation:
    * */
    private val viewModel: ChronometerViewModel by viewModels()
    private lateinit var viewModel2 : Chronometer2ViewModel
    private val viewModelSavedState : SavedStateVM by viewModels()
    private val viewModelCustom : TestCustomVM by viewModels()
    companion object {
        val TAG = "HomeViewModelLiveData_d"
        val TAG2 = "CustomLiveData_d"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeVmactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel2 = ViewModelProvider(this).get(Chronometer2ViewModel::class.java)

        readViewModelChrono()
        readLiveDataChrono()
        readLiveLocation()
        readSavedStateVM()
        readCustomLiveData()
    }

    private fun readCustomLiveData() {
//        viewModelCustom.liveData.setValue("test2")
        Log.d(TAG2, "liveData:${viewModelCustom.liveData.getValue()} ")
    }

    private fun readSavedStateVM() {
        binding.saveBt.setOnClickListener {
            val inputTxt:String? = binding.nameEt.text.toString()
            inputTxt?.let {
                viewModelSavedState.setInputData(it)
            }
        }
        viewModelSavedState.inputLiveData.observe(this, Observer {
            binding.savedVmTv.text =it
        })
    }

    private fun readLiveLocation() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ){
            bindLocationListerer()
            Log.d(TAG, "readLiveLocation: ${mLocationCallback}")
        }
            else  ActivityCompat.requestPermissions(this, locationPermissionList,101)

    }

    private fun bindLocationListerer() {
        Log.d(TAG, "bindLocationListerer: :::")
        BoundLocationManager.bindLocationListenerIn(this,mLocationCallback,applicationContext)
    }

    private fun readLiveDataChrono() {
        // todo: see observe
    viewModel2.mElapsedTime.observe(this, Observer {
        binding.txtChrono2.base = it
        Log.d(TAG, "readLiveDataChrono: $it")
    })
        binding.txtChrono2.setOnClickListener {
            if(viewModel2.getChromoClickState()) viewModel2.setChromoClickstate(false)
            else viewModel2.setChromoClickstate(true)
        }

        viewModel2.isChromoStopped.observe(this, Observer { clickState ->
            if(clickState) binding.txtChrono2.stop()
            else binding.txtChrono2.start()
        })
    }

    private fun readViewModelChrono() {
        if (viewModel.getStartTime() == null) {
            var startTime = SystemClock.elapsedRealtime()
            Log.d(TAG, "mStartTime: ${startTime}")
            viewModel.setStartTime(startTime)
            binding.txtChrono.base =
                SystemClock.elapsedRealtime()      // todo: add a delay & getdata from viewmodel
        } else {
            binding.txtChrono.base = viewModel.getStartTime()!!
        }
        binding.txtChrono.start()


        binding.txtChrono.setOnClickListener {
            Log.d(TAG, "mStartTime: ${viewModel.getStartTime()} ")
            binding.txtChrono.stop()
        }
    }

    private val mLocationCallback = object : LocationListener{
        override fun onLocationChanged(p0: Location) {
            Log.d(TAG, "lat: ${p0.latitude}, long: ${p0.longitude} ")           // todo: why cannot see log printed here
            binding.txtLocation.text = "lat: ${p0.latitude}, long: ${p0.longitude}"
        }
    }
}