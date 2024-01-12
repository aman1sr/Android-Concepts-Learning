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

/* -------------------------------(https://firebase.google.com/docs/storage/android/upload-files)---------------------------
 ---------------------------------(https://heartbeat.comet.ml/working-with-firebase-storage-in-android-part-1-a789f9eea037)-----------
---------------------------------(https://www.geeksforgeeks.org/android-upload-an-image-on-firebase-storage-with-kotlin/)------------------
*
* putBytes() method is the simplest way to upload a file to Cloud Storage.
*               akes a byte[] and returns an UploadTask that you can use to manage and monitor the status of the upload.
*
*todo: putStream()  (UPLOAD FROM STREAM)is the most versatile way to upload a file to Cloud Storage
*               takes an InputStream and returns an UploadTask (https://firebase.google.com/docs/storage/android/upload-files#upload_from_a_stream)
*
* todo: putFile() (UPLOAD FROM LOCAL FILE) upload local files on the device, such as photos and videos from the camera
*                   takes a File and returns an UploadTask  (https://firebase.google.com/docs/storage/android/upload-files#upload_from_a_local_file)
*
*
* */
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
// where inside the  .child() we can specify the folder hierarchy &  $fileName is the actual Image name in the device
            val ref = storageRef.child("image/$fileName")
             val uploadTask =   ref.putFile(filePath)
            uploadTask.addOnProgressListener {
                val completePercent = ((it.bytesTransferred / it.totalByteCount) * 100).toInt()
//                progressBar.progress = completePercent
                Log.d(FirebaseHomeActivity.TAG, "upload completePercent: $completePercent ")
            }
                .addOnSuccessListener {
                    Toast.makeText(this, "Image UPloaded Successfullly", Toast.LENGTH_SHORT).show()
                    progressBar.dismiss()
                }
                .addOnFailureListener{
                    Toast.makeText(this, "Oops!! Something went wrong", Toast.LENGTH_SHORT).show()
                }
                // get the download URL (https://firebase.google.com/docs/storage/android/upload-files#get_a_download_url)
                .continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }
                    ref.downloadUrl
                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUri = task.result
                        Toast.makeText(this, "Download Url!!: $downloadUri", Toast.LENGTH_SHORT).show()
                        Log.d(FirebaseHomeActivity.TAG, "Download Url!!: $downloadUri")
                    } else {
                        Log.e(FirebaseHomeActivity.TAG, "Download URL failed", )
                    }
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

