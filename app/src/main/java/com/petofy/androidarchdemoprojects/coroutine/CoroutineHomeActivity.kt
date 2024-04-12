package com.petofy.androidarchdemoprojects.coroutine

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.petofy.androidarchdemoprojects.databinding.ActivityCoroutineHomeBinding
import kotlinx.coroutines.launch
/*
* todo: read merge vs combine vs zip  (https://kt.academy/article/cc-flow-combine) 
* */
class CoroutineHomeActivity : AppCompatActivity() {
        lateinit var viewModel : ParallelApiViewModel
        lateinit var binding: ActivityCoroutineHomeBinding
        companion object{
            val TAG = "coroutine_d"
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ParallelApiViewModel::class.java)
        binding.button3.setOnClickListener {
            Log.d(TAG, "btn Click: ")
            binding.progressBar.progress = View.VISIBLE
//            viewModel.fetchParrelNetworkcallViaAsync()
            viewModel.fetchParrelNetworkcallViaZipOperator()
        }

        collectFlowState()

    }

    private fun collectFlowState() {  // todo: should i create multiple coorutine launch{} ??
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {    // When the lifecycle state falls below STARTED (e.g., the Activity/Fragment is stopped or destroyed), the coroutine launched within repeatOnLifecycle will be automatically cancelled. (https://g.co/gemini/share/225854e9c851)
            viewModel.isApiCalling.collect {
                binding.progressBar.progress = View.GONE
                Log.d(TAG, "bool: $it")
            }
        }}



    }
}