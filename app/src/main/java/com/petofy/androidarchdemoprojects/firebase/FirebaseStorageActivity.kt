package com.petofy.androidarchdemoprojects.firebase

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.petofy.androidarchdemoprojects.databinding.ActivityFirebaseStorageBinding
import java.io.ByteArrayOutputStream
import java.io.IOException


class FirebaseStorageActivity : AppCompatActivity() {
    lateinit var binding: ActivityFirebaseStorageBinding
    lateinit var filePath: Uri
     var fileName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityFirebaseStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnChoose.setOnClickListener {
            selectImage()
        }
        binding.btnUpload.setOnClickListener {
            uploadImageFirebase()
        }

    }

    private fun uploadImageFirebase() {
        if (filePath != null) {
            val progressBar = ProgressDialog(this)
            progressBar.setTitle("Uploading...")
            progressBar.show()
            
            val storageRef = Firebase.storage.reference
            val uploadTask = storageRef.child("file/$filePath").putFile(filePath)
            // On success, download the file URL and display it
            uploadTask.addOnSuccessListener {
                // using glide library to display the image
                storageRef.child("upload/$fileName").downloadUrl.addOnSuccessListener {
                    Toast.makeText(this, "Download Comleted", Toast.LENGTH_SHORT).show()
                    Log.d(FirebaseHomeActivity.TAG, "Download IMG URL: $it ")
                    progressBar.dismiss()
                }.addOnFailureListener {
                    progressBar.dismiss()
                    Toast.makeText(this, "Download Failed", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                progressBar.dismiss()
                Toast.makeText(this, "Download Failed", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        startActivityForResult(Intent.createChooser(intent,"Select Image from here..."),101)
    }


    // Override onActivityResult method
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )

        if (requestCode == 101 && resultCode == RESULT_OK && data != null && data.data != null) {

            // Get the Uri of selected Img
            filePath = data.data!!
            Log.d(FirebaseHomeActivity.TAG, "filePath URI : $filePath")

            // extract the file name with extension
            fileName = getFileName(applicationContext, filePath)
            Log.d(FirebaseHomeActivity.TAG, "fileName : $fileName")

            try {

                // Setting image on image view using Bitmap
                val bitmap = MediaStore.Images.Media
                    .getBitmap(
                        contentResolver,
                        filePath
                    )
                binding.imgView.setImageBitmap(bitmap)
            } catch (e: IOException) {
                // Log the exception
                e.printStackTrace()
            }
        }
    }

    @SuppressLint("Range")
    private fun getFileName(context: Context, uri: Uri): String? {
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            cursor.use {
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        return cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                    }
                }
            }
            Log.d(FirebaseHomeActivity.TAG, "getFileName-> inside--> uri.scheme = content:>>> cursor: $cursor ")
        }
        Log.d(FirebaseHomeActivity.TAG, "getFileName-> uri.path: ${uri.path} ")
        Log.d(FirebaseHomeActivity.TAG, "uri.scheme: ${uri.scheme} ")
        return uri.path?.lastIndexOf('/').let {
            uri.path?.substring(it!!)
        }.toString()
    }
}