package com.petofy.androidarchdemoprojects.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.compose.ui.theme.AndroidArchDemoProjectsTheme

/*
* cheezy : ved 8: https://www.youtube.com/watch?v=zdCrYONv-ec&list=PLRKyZvuMYSIO9sadcCwR0DR8UPi9bQlev&index=8
*
* todo: check compose doc: https://developer.android.com/jetpack/compose/mental-model#:~:text=Jetpack%20Compose%20is%20a%20modern,without%20imperatively%20mutating%20frontend%20views.
* */
class RecompositionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            recomposable()
            NotificationScreen()
        }
    }
}

/*ved8: Compose State concept */
@Preview
@Composable
fun NotificationScreen() {
    var count : MutableState<Int> = rememberSaveable {
        mutableStateOf(0)
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(1f)
    ) {
        Column {
            NotificationCounter()       // can't update count
            NotificationCounter2()      // can remember the state after btn count by : remember{}

            /*state hosting*/
            NotificationCounter3(count.value,{count.value++})      // can survive screen Orientation by : rememberSaveable{}
            MessageBar(count.value)
        }
    }
}

@Composable
fun MessageBar(count: Int) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.ic_fav), contentDescription = "")
            Text(text = "Message send : $count")
        }
    }
}

@Composable
fun NotificationCounter3(count: Int, increment: ()-> Unit) {

    Column(verticalArrangement = Arrangement.Center) {
        Text(text = "You have send ${count} notification")
        Button(onClick = {
//            count.value++
            increment.invoke()
            Log.d("CoderTag_d", "Btn clicked: ")
        }) {
            Text(text = "Send Notification")
        }
    }
}

@Composable
fun NotificationCounter2() {
    var count: MutableState<Int> = remember {
        mutableStateOf(0)
    }
    Column(verticalArrangement = Arrangement.Center) {
        Text(text = "You have send ${count.value} notification")
        Button(onClick = {
            count.value++
            Log.d("CoderTag_d", "Btn clicked: ")
        }) {
            Text(text = "Send Notification")
        }
    }
}

@Composable
fun NotificationCounter() {
    var count = 0
    Column(verticalArrangement = Arrangement.Center) {
        Text(text = "You have send $count notification")
        Button(onClick = {
            count++
            Log.d("CoderTag_d", "Btn clicked: ")
        }) {
            Text(text = "Send Notification")
        }
    }
}

/*ved7 : recomposition*/
@Preview
@Composable
fun recomposable() {
    // NOTE: mutableStateOf --  the role of mutableStateOf is to manage a value that can change over time and trigger UI updates (recompositions) when those changes happen.
    val state = remember {
        mutableStateOf(0.0)
    }
    Log.d("recomposable_d", "recomposable during initial Logged: ")
    Button(onClick = {
        state.value = Math.random()
    }) {
        Log.d(
            "recomposable_d",
            "recomposable during both composition & recomposition: ${state.value.toString()}"
        )
        Text(text = state.value.toString())
    }
}