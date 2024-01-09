package com.petofy.androidarchdemoprojects

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.petofy.androidarchdemoprojects.arcore.ARCoreActivity
import com.petofy.androidarchdemoprojects.dagger.cheezyCode.DaggerCheezyCodeActivity
import com.petofy.androidarchdemoprojects.dagger.sharedpref.DaggerSharedPrefActivity
import com.petofy.androidarchdemoprojects.databinding.ActivityMainBinding
import com.petofy.androidarchdemoprojects.dialog.DialogActivity
import com.petofy.androidarchdemoprojects.theme.ThemeActivity
import com.petofy.androidarchdemoprojects.firebase.FirebaseAuthActivity
import com.petofy.androidarchdemoprojects.firebase.FirebaseHomeActivity
import com.petofy.androidarchdemoprojects.flow.FlowActivity
import com.petofy.androidarchdemoprojects.lambda.RecHomeActivity
import com.petofy.androidarchdemoprojects.permission.PermissionActivity
import com.petofy.androidarchdemoprojects.utils.Utils.startScreen
import com.petofy.androidarchdemoprojects.webview.HomeWebViewActivity

class MainActivity : AppCompatActivity() {
    companion object{
       private val TAG= "HOME_SCREEN_ARCH_d"
        private val WEBVIEW_REQUEST_CODE = 101
        val INTENT_WEBVIEW_KEY = "INTENT_WEBVIEW_KEY"
    }
val openHomeWebView = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
    if (result.resultCode == Activity.RESULT_OK) {
        val quizzReturnData = result.data?.getStringExtra(INTENT_WEBVIEW_KEY)
        Log.d(TAG, "quizzReturnData:$quizzReturnData ")
    }
}
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.theme.setOnClickListener {
          startScreen(this,ThemeActivity::class.java)
        }
        binding.webView.setOnClickListener {
            intent = Intent(this, HomeWebViewActivity::class.java)
            openHomeWebView.launch(intent)
        }
        binding.dialog.setOnClickListener {
            startScreen(this,DialogActivity::class.java)
        }
        binding.firebase.setOnClickListener {
            startScreen(this,FirebaseHomeActivity::class.java)
        }
        binding.webView.setOnClickListener {
            startScreen(this,HomeWebViewActivity::class.java)
        }
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
            startScreen(this,ARCoreActivity::class.java)
        }
        binding.lambda.setOnClickListener {
            startScreen(this,RecHomeActivity::class.java)
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

}