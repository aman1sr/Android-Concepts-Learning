package com.petofy.androidarchdemoprojects.br

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

// (https://androidgeek.co/broadcast-receiver-in-kotlin-beginner-in-depth-guide-7ea3d503fc92)
class AirplaneModeChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirplaneModeEnabled = intent?.getBooleanExtra("state", false) ?: return
        if (isAirplaneModeEnabled) {
            Toast.makeText(context, "Airplane Mode Enabled", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Airplane Mode disabled...", Toast.LENGTH_LONG)
        }

    }
}