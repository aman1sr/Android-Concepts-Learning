package com.petofy.androidarchdemoprojects.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.petofy.androidarchdemoprojects.databinding.ActivityFlowBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis
/*
* Flow 101 proDev (https://proandroiddev.com/kotlin-flows-in-android-summary-8e092040fb3a)
* amitShekhar git (https://github.com/amitshekhariitbhu/Learn-Kotlin-Flow)
* */
class FlowActivity : AppCompatActivity() {
    companion object{
        val TAG = "FlowActivity_d"
    }
    lateinit var binding: ActivityFlowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.basicFlow.setOnClickListener {
            basicFlowConsuming()
        }
        binding.multipleFlow.setOnClickListener {
            multipleFlowConsuming()
        }
        binding.cancelFlow.setOnClickListener {
            cancelTheFlow()
        }

        binding.actionFlow.setOnClickListener {
            actionsOfFlow()
        }
      binding.terminalOperatorFlow.setOnClickListener {
          /*
          * Terminal operators initiate the actual collection of items emitted by a Kotlin Flow. They mark the end of a flow processing pipeline and produce a result or side effect.
          * */
          terminalOperator()
      }
      binding.nonTerminalOperatorFlow.setOnClickListener {
          /*
          * Non-terminal operators transform or modify an existing Kotlin Flow, creating a new Flow as a result. They act like steps in a processing chain.
          * */
          nonTerminalOperator()
      }
      binding.filteredDataClassFlow.setOnClickListener {
          filteredDataClass()
      }
      binding.bufferFlow.setOnClickListener {
          bufferFlow()
      }

 /*
 * works upStream in nature therefore will  change the thread on above .onFlow() line
 * NOTE: can have multple .onFlow() line in bw actions to change the thrread
 * */
      binding.upStreamFlow.setOnClickListener {
          upStreamOnFlow()
      }

        binding.exceptionHandlingFlow.setOnClickListener {
            exceptionHandling()
        }

// NOTE: hot vs cold  (https://g.co/gemini/share/cd6b4e1c0deb)
        /*
        * Shared & State can have multiple consumer
        * Shared doesn't need to have def value
        * State need def value & it retain the last state value emitted
        *
        * */
        binding.hotSharedFlow.setOnClickListener {
            checkSharedFlow()           // shared Flow when emits value, which are being read by mutliple consumer,, doesn't care if they are late,,skipped is skipped
        }

        binding.hotStateFlow.setOnClickListener {
            checkStateFlow()            //
        }
    }



    private fun checkStateFlow() {
        GlobalScope.launch {
            val result = stateFlowProducer()
            delay(1500)
            result.collect{
                Log.d(TAG, "CollectedFlowItem1: $it")
//                Log.d(TAG, "result: ${result.value}")     // can't access the MutableStateFlow def value, becuase return type is Flow<Int>
            }
        }

            GlobalScope.launch {
            val result = stateFlowProducer2()
//            val result = stateFlowProducer()
            delay(6000)                     // here delay time 6s is > delay stateFlowProducer2() 4s ,, but last state of value will is retained and will get showed
            result.collect{
                Log.d(TAG, "CollectedStateFlowItem2: $it")
                Log.d(TAG, "result: ${result.value}")     // could access the StateFlow value, becuase, return type is StateFlow<Int>
            }
        }


    }

    private fun checkSharedFlow() {
        GlobalScope.launch {
            val result = sharedFlowProducer()
            result.collect {
                Log.d(TAG, "item: $it")
            }
        }

        //  here becuase added replay(1) in sharedFlowProducer() below coroutine will get 1 skipped item extra
        GlobalScope.launch {
            val result = sharedFlowProducer()
            delay(2500)
            result.collect {
                Log.d(TAG, "item2: $it")
            }
        }
    }

    /*
    * Hot in nature (like threater)
    * */
    private fun sharedFlowProducer(): Flow<Int> {
        val mutableSharedFlow = MutableSharedFlow<Int>(1)       // adding a replay(1) will store 1 item in buffer such that the delayed customer will get 1 extra skipped item
        GlobalScope.launch {
            val list = listOf<Int>(1, 2, 3, 4, 5)
            list.forEach {
                mutableSharedFlow.emit(it)
                delay(1000)
            }
        }
        return mutableSharedFlow
    }

    /*
    * State Flow are also hot in nature, where it's last state is saved as single buffer
    * */
    private fun stateFlowProducer(): Flow<Int> {
        val mutableStateFlow = MutableStateFlow(10)
        GlobalScope.launch {
            val list = listOf<Int>(11, 22, 33, 44, 55)
            list.forEach {
                mutableStateFlow.emit(it)
                delay(1000)
            }
        }
        return mutableStateFlow
    }

    /*
    * return type: StateFlow   therefore could acces the def value also
    * */
    private fun stateFlowProducer2(): StateFlow<Int> {
        val mutableStateFlow = MutableStateFlow(10)
        GlobalScope.launch {
            delay(2000)
            mutableStateFlow.emit(20)
            delay(2000)
            mutableStateFlow.emit(30)
        }
        return mutableStateFlow
    }

    private fun exceptionHandling() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                producer3()
                    .collect {
                        Log.d(TAG, "$it, Collected thread: ${Thread.currentThread().name}")
                    }
            } catch (e: Exception) {
                Log.d(TAG, "${e.message.toString()}")
            }

        }
    }

    private fun upStreamOnFlow() {
        GlobalScope.launch(Dispatchers.Main) {
            producer2()
                .map {
                    delay(1000)
                    it * 2
                    Log.d(TAG, "Map thread: ${Thread.currentThread().name}")
                }
                .filter {
                    delay(1000)
                    Log.d(TAG, "Filter Thread: ${Thread.currentThread().name}")
                    it < 8
                }
                .flowOn(Dispatchers.IO)         // works upStream (ie, will change the thread on above this line ), below flowOn line will exe. normal Main Thread,
                .collect {
                    Log.d(TAG, "collected in Thread : ${Thread.currentThread().name} ")
                }
        }
    }

    private fun bufferFlow() {
        GlobalScope.launch {
            /*
            * here each item is taking 2.5 sec (1s by producer & 1.5s by consumer)
            * therefore here adding buffer would add 3 in it.
            *
            * */
            val time = measureTimeMillis {
                producer()
                    .buffer(3)
                    .collect {
                        delay(1500)
                        Log.d(TAG, "${it.toString()}")
                    }
            }
            Log.d(TAG, "${time.toString()}")
        }
    }

    private fun filteredDataClass() {
        GlobalScope.launch {
            getNotes()
                .map {
                    FormattedNote(it.isActive, it.title.uppercase(), it.description)
                }
                .filter {
                    it.isActive
                }
                .collect {
                    Log.d(TAG, "${it.toString()}")
                }
        }
    }

    private fun getNotes(): Flow<Note>{
        val list = listOf<Note>(
            Note(1,true,"First", "FirstDesc"),
            Note(2,true,"Second", "SecondDesc"),
            Note(3,false,"Third", "ThirdDesc")
        )
        return list.asFlow()
    }
    private fun terminalOperator() {
        GlobalScope.launch {
            val result = producer().toList()
            val firstResult = producer().first()
            val collectResult = producer().first()
            Log.d(TAG, "LIST: ${result.toString()}")
            Log.d(TAG, "First elemnet: ${collectResult}")
        }
    }

    private fun nonTerminalOperator() {
        GlobalScope.launch {
            producer()
                .map {
                    it * 2
                }
                .filter {
                    it < 8
                }
                .collect {
                    Log.d(TAG, "${it.toString()}")
                }
        }
    }

    private fun actionsOfFlow() {
        GlobalScope.launch(Dispatchers.Main) {
            producer()
                .onStart {
//                    emit(-1)
                    Log.d(TAG, "Starting out")
                }
                .onCompletion {
                    emit(6)
                    Log.d(TAG, "Completed")
                }
                .onEach {
                    Log.d(TAG, "about to emit - $it")
                }
                .collect {
                    Log.d(TAG, "${it.toString()}")
                }
        }
    }

    private fun cancelTheFlow() {
        val job =   GlobalScope.launch {
            val data: Flow<Int> = producer()
            data.collect {
                Log.d(TAG, " consuming: ${it.toString()}")
            }
        }

        GlobalScope.launch {
            delay(2500)
            job.cancel()
        }
    }

    private fun multipleFlowConsuming() {
        basicFlowConsuming()                // consumer 1

        GlobalScope.launch {        // consumer 2 (cold flow: ott system, late shuru hua but complete pura hoga)
            val data: Flow<Int> = producer()
            delay(2500)
            data.collect {
                Log.d(TAG, " consumed_d1: ${it.toString()}")
            }
        }
    }


    private fun basicFlowConsuming() {         //COLD FLOW  why becuase - it sense the data generation logic only executes on demand when a collector starts listening.(https://g.co/gemini/share/13e9df3d64a4)
        GlobalScope.launch {            // unlike HOT Flow - Starts when the flow is created, can emit before collectors
            val data: Flow<Int> = producer()
            data.collect {
                Log.d(TAG, " consumed_d2: ${it.toString()}")
            }
        }
    }

    fun producer() = flow<Int> {
        val list = listOf(1, 2, 3, 4, 5,6,7,8,9)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }
    private fun producer2(): Flow<Int>{
        return flow {
            val list = listOf<Int>(1, 2, 3, 4, 5)
            list.forEach {
                delay(1000)
                Log.d(TAG, "emitter thread - ${Thread.currentThread().name}")
                emit(it)
            }
        }
    }
    private fun producer3(): Flow<Int>{
        return flow {
            val list = listOf<Int>(1, 2, 3, 4, 5)
            list.forEach {
                delay(1000)
                Log.d(TAG, "emitter thread - ${Thread.currentThread().name}")
                if(it==4)throw Exception("Error in Emitter")
                emit(it)

            }
        }.catch {       // to handle the producer Exception seperately, + could handle emit also
            Log.e(TAG, "Emitter catch: ${it.message}", )
            emit(-1)
        }
    }


}