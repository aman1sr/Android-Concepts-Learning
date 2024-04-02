package com.petofy.androidarchdemoprojects.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petofy.androidarchdemoprojects.R
import com.petofy.androidarchdemoprojects.compose.ui.theme.AndroidArchDemoProjectsTheme

class DemoComposeList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // NOTE: alternative to RecyclerView in Jetpack Compose
            LazyColumn(content = {
                items(getCategoryList()) { item ->
                    BlogCategory(img = item.img, title = item.title, desc = item.desc)
                }
            })



            // NOTE: Column doesn't have def state of scrolling, can acheive it as : rememberScrollState
            //      disAdv: will render all item at once, can't recycle
 /*             Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                getCategoryList().map {
                    BlogCategory(img = it.img, title = it.title, desc = it.desc)
                }
            }
*/

        }
    }
}

@Preview(heightDp = 500)
@Composable
fun BlogCategory(img: Int, title: String, desc: String) {
    Card(
        elevation = 8.dp,
        modifier = Modifier.padding(8.dp)
    ) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        Image(painter = painterResource(id = img), contentDescription ="pic" ,
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
            )
        showDescription(title, desc, Modifier.weight(.8f))
    }
    }
}

@Composable
private fun showDescription(title: String, desc: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = desc,
            fontWeight = FontWeight.Thin,
            fontSize = 12.sp
        )
    }
}

data class Category(val img: Int, val title: String, val desc: String)

fun getCategoryList(): MutableList<Category> {
    val list = mutableListOf<Category>()

    list.add(Category(R.drawable.ic_fav, "Programming","js, python, go"))
    list.add(Category(R.drawable.ic_account, "Programming","kotlin, java, c++"))
    list.add(Category(R.drawable.ic_fav, "Programming","js, python, go"))

    list.add(Category(R.drawable.ic_fav, "Programming","js, python, go"))
    list.add(Category(R.drawable.ic_account, "Programming","ruby, dart"))
    list.add(Category(R.drawable.ic_fav, "Programming","js, python, go"))

    list.add(Category(R.drawable.ic_arrow, "Programming","html, css"))
    list.add(Category(R.drawable.ic_fav, "Programming","js, python, go"))

    list.add(Category(R.drawable.ic_arrow, "Programming","html, css"))
    list.add(Category(R.drawable.ic_fav, "Programming","js, python, go"))
    list.add(Category(R.drawable.ic_arrow, "Programming","html, css"))

    return list
}