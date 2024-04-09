package com.petofy.androidarchdemoprojects.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.databinding.ActivityFlowBinding
import com.petofy.androidarchdemoprojects.databinding.ActivityFlowKodecoBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
/*
* kodeco:p1 (https://www.kodeco.com/9799571-kotlin-flow-for-android-getting-started#toc-anchor-002)
*
*
* */
class FlowKodecoActivity : AppCompatActivity() {
    lateinit var binding: ActivityFlowKodecoBinding
    companion object{
        val TAG = "FlowKodeco_d"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowKodecoBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        processValues()
//        processValues2()

        binding.basicFlow.setOnClickListener {
            runBlocking {
              val names=  checkFlowBuilder()
                names.map { name -> name.length }
                    .filter { length -> length<5 }
                    .collect{
                        Log.d(TAG, "onCreate: $it")
                    }


            }
        }

    
    }

    private fun checkFlowBuilder() = flow<String> {
            val names = listOf("Jody", "Steve", "Lance", "Joe")
            for (name in names) {
                delay(1000)
                emit(name)
            }
    }

    private fun processValues2() {
        runBlocking {
            val values = getValues2()
            for (value in values) {
                Log.d(TAG, "value: $value")
            }
        }
    }

    suspend fun getValues2(): Sequence<Int> = sequence {
        Thread.sleep(1000)
        yield(1)
        Thread.sleep(1000)
        yield(2)
        Thread.sleep(1000)
        yield(3)

    }

    private fun processValues() {
        runBlocking {
            val values = getValues()
            for (value in values) {
                Log.d(TAG, "value: $value")
            }
        }
    }

    suspend fun getValues(): List<Int> {
        delay(1000)
        return listOf(1,2,3)
    }
}