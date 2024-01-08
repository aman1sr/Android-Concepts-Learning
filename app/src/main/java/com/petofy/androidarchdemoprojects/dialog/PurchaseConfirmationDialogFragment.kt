package com.petofy.androidarchdemoprojects.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class PurchaseConfirmationDialogFragment : DialogFragment() {
    companion object{
         val TAG = "PurchaseConfirmationDialog"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("Are you confirming your Purchase")
            /*
            * text is set to "OK", indicating the button's primary action.
            * listener defines a lambda function that displays a Toast message when the button is clicked.
            * */
            .setPositiveButton("OK"){ text, listener ->
                Toast.makeText(context, "Clicked!!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Dismiss"){_,_ ->
                Toast.makeText(context, "Dismiss", Toast.LENGTH_SHORT).show()
                dismiss()
            }
            .create()
}