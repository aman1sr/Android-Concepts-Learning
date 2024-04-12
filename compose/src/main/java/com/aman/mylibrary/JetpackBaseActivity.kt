package com.aman.mylibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aman.mylibrary.ui.theme.AndroidArchDemoProjectsTheme

class JetpackBaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidArchDemoProjectsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn{
                        items(loadAffirmations()){item->
                            listItem(item = item, modifier = Modifier.padding(5.dp))
                        }
                    }
                }
            }
        }
    }
}
fun loadAffirmations(): List<Item>{
    return listOf<Item>(
        Item("abc",R.drawable.pacquiao),
        Item("xxx",R.drawable.pacquiao),
        Item("sddd",R.drawable.pacquiao),
        Item("as",R.drawable.pacquiao),
        Item("asdf",R.drawable.pacquiao),
        Item("sd",R.drawable.pacquiao)
    )
}

@Composable
fun listItem(item: Item, modifier: Modifier) {
    Column {
        Image(painter = painterResource(id = item.img), contentDescription = "${item.desc}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()

        )
        Text(text = item.desc,
            modifier = Modifier.padding(5.dp),
            style = MaterialTheme.typography.headlineMedium
            )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


