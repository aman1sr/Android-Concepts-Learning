package com.petofy.androidarchdemoprojects.other

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityConfigChangeBinding

class ConfigChangeActivity : AppCompatActivity() {
    lateinit var binding: ActivityConfigChangeBinding

    companion object {
        val TAG = "ConfigChange_d"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            openFragmentManager()
//            openFrgIntent()
        }


    }

    private fun openFrgIntent() {

    }

    private fun openFragmentManager() {
        val frg = ConfigChangeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, frg)
            .addToBackStack(null) // Optional
            .commit()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ACTIVITY")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ACTIVITY")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ACTIVITY")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ACTIVITY")

    }

}