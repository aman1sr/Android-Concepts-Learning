package com.petofy.androidarchdemoprojects.dagger.sharedpref

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityDaggerSharedPrefBinding
import javax.inject.Inject

class DaggerSharedPrefActivity : AppCompatActivity() {
    lateinit var binding : ActivityDaggerSharedPrefBinding

//    @Inject
//    lateinit var sharedPreferences : SharedPreferences
//    private lateinit var sharedPreferenceComponent: SharedPreferenceComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaggerSharedPrefBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener {
            saveInputField()
        }
        binding.getBtn.setOnClickListener {
            displaySavedField()
        }

//        sharedPreferenceComponent =


    }

    private fun displaySavedField() {
//        binding.outputField.text = sharedPreferences.getString("inputField", "")
    }

    private fun saveInputField() {
//        val editor = sharedPreferences.edit()
//        editor.putString("inputField", binding.inputField.text.toString().trim())
//        editor.apply()
    }
}