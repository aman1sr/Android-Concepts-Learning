package com.petofy.androidarchdemoprojects.compose

import android.os.Bundle
import android.transition.CircularPropagation
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.compose.ui.theme.AndroidArchDemoProjectsTheme

class DemoComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                PreviewCircularImage()
                PreviewFunction9()

                PreviewFunction8(R.drawable.ic_account, "Aman", "Software Dev")
                PreviewFunction8(R.drawable.ic_fav, "Aman2", "Software Dev+")
                PreviewFunction8(R.drawable.ic_arrow, "Ama3", "Software Dev++")
                PreviewFunction8(R.drawable.ic_arr, "Aman4", "Software Dev++")
            }
        }
    }

    /*
    * ved 5 (cheezy) Layout compose : modifiers
    *
    * */

    @Preview
    @Composable
    private fun PreviewCircularImage() {
        Image(painter = painterResource(id = R.drawable.mma),
            contentScale = ContentScale.Crop,

            contentDescription = "",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(2.dp, Color.LightGray, CircleShape)
            )
    }

    @Preview
    @Composable
    private fun PreviewFunction9() {
        Text(text = "Aman",
            color = Color.Magenta,
            modifier = Modifier
                .background(Color.Blue)
                .size(200.dp)
                .padding(36.dp)
                .border(4.dp, Color.Red)
                .clip(CircleShape)        // cuts the UI in particular shape
                .background(Color.Yellow)
                .clickable { }
        )

    }

    /*
    * ved4: (cheezy) Layout compose : column, row, Box , Demo EX of Card
    * */

    //    @Preview(showBackground = true, widthDp = 200, heightDp = 200)
    @Composable
    private fun PreviewFunction8(imgID: Int, name: String, occupation: String) {
        Row(Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = imgID),
                contentDescription = "",
                Modifier.size(40.dp)
            )
            Column {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = occupation,
                    fontWeight = FontWeight.Thin,
                    fontSize = 12.sp
                )
            }
        }
    }


    @Composable
    private fun PreviewFunction7() {

        Box(contentAlignment = Alignment.TopEnd) {
            Image(painter = painterResource(id = R.drawable.ic_fav), contentDescription = "Heart")
            Image(painter = painterResource(id = R.drawable.ic_arrow), contentDescription = "Arr")
        }
    }

    //    @Preview(showBackground = true, widthDp = 300, heightDp = 500)
    @Composable
    private fun PreviewFunction6() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(text = "P", fontSize = 24.sp)
            Text(text = "Q", fontSize = 24.sp)
        }
    }

    //    @Preview(showBackground = true, widthDp = 300, heightDp = 500)
    @Composable
    private fun PreviewFunction5() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "A", fontSize = 24.sp)
            Text(text = "B", fontSize = 24.sp)
        }
    }


    /*
    * BASIC: Button , Text, TextField, Image.....................................
    * */

    @Composable
    private fun PreviewFunction4() {

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Red,
                // todo: why not able to call bgColor
            ),
            enabled = false
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow),
                contentDescription = "arrow"
            )
        }
    }

    @Composable
    private fun PreviewFunction3() {
        Image(
            painter = painterResource(id = R.drawable.ic_fav),
            contentDescription = "Heart",
            colorFilter = ColorFilter.tint(Color.Blue),
            contentScale = ContentScale.Crop
        )
    }

    @Composable
    private fun PreviewFunction2() {
        Text(
            text = "Hello Aman",
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            textAlign = TextAlign.Center,
            fontSize = 36.sp
        )
    }

    //        @Preview(showBackground = true, name = "Hello Message", widthDp = 200, heightDp = 200)
    @Composable
    fun PreviewFunction1() {
        val state = remember { mutableStateOf("Hello") }
        TextField(
            value = state.value,
            onValueChange = { state.value = it },
            label = { Text(text = "Enter Message") },
            placeholder = {}
        )
    }


    @Composable
    fun SayCheezy(name: String) {
        Text(text = "Hello $name")
    }

    @Composable
    private fun PreviewFunction() {
        SayCheezy(name = "Aman")
    }

}

