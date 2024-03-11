package com.petofy.androidarchdemoprojects.ml

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.petofy.androidarchdemoprojects.databinding.ActivityHomeMlactivityBinding
import org.tensorflow.lite.support.image.TensorImage
/*
* src: (https://www.youtube.com/watch?v=7Sb_m5GxQLA&list=LL)
* */
class BirdClassifyMLActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeMlactivityBinding
    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { result: Uri? ->
            val imageUri: Uri? = result  // Extract Uri here
            imageUri?.let {
                // ... (process the imageUri as needed)
                val inputStream = contentResolver.openInputStream(it)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                binding.imgBird.setImageBitmap(bitmap)
                generateMLprediction(bitmap)
            }
        }

    private fun generateMLprediction(bitmap: Bitmap?) {
        try {
            val model = BirdClassificationModel.newInstance(this)

            // Creates inputs for reference.
            val image = TensorImage.fromBitmap(bitmap)

            // Runs model inference and gets result.
            val outputs = model.process(image)
            val probability = outputs.probabilityAsCategoryList
            probability.map {
                Log.d("probability_d", "displayName: ${it.displayName} >> score:${it.score} , label: ${it.label} ")
            }
                val maxOutput = probability.maxBy {
                    it.score
                }
            binding.txtOutput.text = maxOutput.label.toString()

            // Releases model resources if no longer used.
            model.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeMlactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoad.setOnClickListener {
            imagePickerLauncher.launch("image/*")
        }


    }
}