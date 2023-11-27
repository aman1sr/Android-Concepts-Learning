package com.petofy.androidarchdemoprojects.permission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.petofy.androidarchdemoprojects.R

class PermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        val intent = intent.getStringExtra("type")

        intent?.let {
            when (it.toString()) {
                "single" -> {
                    checkSinglePermission()
                }
            else ->  Toast.makeText(this, "Invalid stuff..", Toast.LENGTH_SHORT).show()

            }

        }
    }

    private fun checkSinglePermission() {
        Toast.makeText(this, "Single Permission", Toast.LENGTH_SHORT).show()
    }
}