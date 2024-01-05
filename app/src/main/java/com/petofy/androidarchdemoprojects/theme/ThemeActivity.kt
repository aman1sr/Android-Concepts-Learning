package com.petofy.androidarchdemoprojects.theme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityThemeBinding

/*
* codeLab: (https://developer.android.com/codelabs/basic-android-kotlin-training-change-app-theme?hl=en&authuser=0#2)
* */
class ThemeActivity : AppCompatActivity() {
    lateinit var binding: ActivityThemeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /*
        * isEnabled controls whether the switch can be interacted with.
        * isActivated controls the visual appearance of the switch, indicating its "active" state.
        * isChecked represents the actual on/off state of the switch.
        * */
        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Night Mode ON!", Toast.LENGTH_SHORT).show()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }



    }
}