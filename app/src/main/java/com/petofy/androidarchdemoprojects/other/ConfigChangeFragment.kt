package com.petofy.androidarchdemoprojects.other

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.FragmentConfigChangeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConfigChangeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConfigChangeFragment : Fragment() {
  lateinit var binding: FragmentConfigChangeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentConfigChangeBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.d(ConfigChangeActivity.TAG, "onStart: FRG")
    }

    override fun onResume() {
        super.onResume()
        Log.d(ConfigChangeActivity.TAG, "onResume: FRG")
    }

    override fun onStop() {
        super.onStop()
        Log.d(ConfigChangeActivity.TAG, "onStop: FRG")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(ConfigChangeActivity.TAG, "onDestroy: FRG")

    }



}