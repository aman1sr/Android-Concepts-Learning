package com.petofy.androidarchdemoprojects.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityDialogBinding

class DialogActivity : AppCompatActivity() {

    lateinit var binding: ActivityDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDialogFrg.setOnClickListener {
            showAlertDialogFrg()
        }

    }

    /*
    * When creating a DialogFragment from within a Fragment, use the fragment's childFragmentManager  so that the state properly restores after configuration changes.
    * */
    private fun showAlertDialogFrg() {
        PurchaseConfirmationDialogFragment().show(supportFragmentManager,PurchaseConfirmationDialogFragment.TAG)
    }
}