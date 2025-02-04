package com.example.photo_gallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.photo_gallery.ui.theme.PhotoGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotoGalleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ImageGrid(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ImageGrid(modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 10.dp) // avoid camera
    ) {
        Row {
            ImageWithCaption("house")
            Spacer(modifier = Modifier.width(10.dp))
            ImageWithCaption("tree")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            ImageWithCaption("xbox")
            Spacer(modifier = Modifier.width(10.dp))
            ImageWithCaption("bike")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            ImageWithCaption("school")
            Spacer(modifier = Modifier.width(10.dp))
            ImageWithCaption("cat")
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun ImageWithCaption(name: String) {
    Column(modifier = Modifier.width(175.dp)) {
        Image(
            painter = painterResource(id = when (name) {
                "house" -> R.drawable.house
                "tree" -> R.drawable.orangetree
                "xbox" -> R.drawable.xbox
                "bike" -> R.drawable.bikewithkid
                "school" -> R.drawable.blavatnik_school
                "cat" -> R.drawable.cat
                else -> R.drawable.ic_launcher_foreground
            }),
            contentDescription = "a house",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(175.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Text(when (name) {
            "house" -> "- A white house with trees and a large driveway."
            "tree" -> "- An orange tree in California."
            "xbox" -> "- An old XBox from before the 360 generation."
            "bike" -> "- A man riding a bike with his child in the back."
            "school" -> "- The Blavatnik School of Government."
            "cat" -> "- A cat with black and white fur resting on a cushion."
            else -> "Image not found for: $name"
        }, color = Color.White)
    }
}