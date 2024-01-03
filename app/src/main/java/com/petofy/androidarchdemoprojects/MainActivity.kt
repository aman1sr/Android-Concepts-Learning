package com.petofy.androidarchdemoprojects

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.petofy.androidarchdemoprojects.arcore.ARCoreActivity
import com.petofy.androidarchdemoprojects.dagger.cheezyCode.DaggerCheezyCodeActivity
import com.petofy.androidarchdemoprojects.dagger.sharedpref.DaggerSharedPrefActivity
import com.petofy.androidarchdemoprojects.databinding.ActivityMainBinding
import com.petofy.androidarchdemoprojects.firebase.FirebaseActivity
import com.petofy.androidarchdemoprojects.flow.FlowActivity
import com.petofy.androidarchdemoprojects.lambda.RecHomeActivity
import com.petofy.androidarchdemoprojects.permission.PermissionActivity

class MainActivity : AppCompatActivity() {
    companion object{
        val TAG= "HOME_SCREEN_ARCH_d"
    }

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.daggerBasics.setOnClickListener {
            runDaggerDemo()
        }
        binding.daggerBasics2.setOnClickListener {
            runDaggerDemo2()
        }
        binding.permission.setOnClickListener {
            checkSimpleCameraPermission()
        }
        binding.permission2.setOnClickListener {
            checkCameraLocationPermission()
        }
        binding.flow.setOnClickListener {
            checkFlowConcept()
        }
        binding.arCore.setOnClickListener {
            startScreen(ARCoreActivity::class.java)
        }
        binding.lambda.setOnClickListener {
            startScreen(RecHomeActivity::class.java)
        }
        binding.firebase.setOnClickListener {
            startScreen(FirebaseActivity::class.java)
        }

    }



    private fun checkFlowConcept() {
        intent = Intent(this, FlowActivity::class.java)
        startActivity(intent)
    }
    private fun checkCameraLocationPermission() {
        intent = Intent(this, PermissionActivity::class.java)
        intent.putExtra("type","double")
        startActivity(intent)
    }
    private fun checkSimpleCameraPermission() {
        intent = Intent(this, PermissionActivity::class.java)
        intent.putExtra("type","single")
        startActivity(intent)
    }

    private fun runDaggerDemo() {
        intent = Intent(this, DaggerSharedPrefActivity::class.java)
        startActivity(intent)
    }
    private fun runDaggerDemo2() {
        intent = Intent(this, DaggerCheezyCodeActivity::class.java)
        startActivity(intent)
    }
    fun startScreen(className: Class<*>) {
        val intent = Intent(this, className)
        startActivity(intent)
    }
}