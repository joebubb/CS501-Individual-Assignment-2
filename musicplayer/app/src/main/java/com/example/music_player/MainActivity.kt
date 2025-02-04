package com.example.music_player

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.music_player.ui.theme.MusicPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicPlayerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MusicPlayer(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MusicPlayer(modifier: Modifier) {
    Column(
        modifier = Modifier
        .fillMaxSize()
        .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        AlbumCover()
        Spacer(modifier = Modifier.height(32.dp))
        SongText()
        Spacer(modifier = Modifier.height(8.dp))
        ButtonRow()
        Spacer(modifier = Modifier.height(32.dp))
        ProgressBar()
    }
}

@Composable
fun AlbumCover() {
    Image(
        painter = painterResource(id = R.drawable.eternal_atake),
        contentDescription = "The Eternal Atake album cover",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(350.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(14.dp))
    )
}

@Composable
fun ButtonRow() {
    Row {
        Button(onClick = {}) {
            Icon(painter = painterResource(R.drawable.backward), contentDescription = "back button")
        }
        Spacer(modifier = Modifier.width(4.dp))
        Button(onClick = {}) {
            Icon(painter = painterResource(R.drawable.play), contentDescription = "play button")
        }
        Spacer(modifier = Modifier.width(4.dp))
        Button(onClick = {}) {
            Icon(painter = painterResource(R.drawable.pause), contentDescription = "pause button")
        }
        Spacer(modifier = Modifier.width(4.dp))
        Button(onClick = {}) {
            Icon(painter = painterResource(R.drawable.forward), contentDescription = "skip button")
        }
    }
}

@Composable
fun SongText() {
    Column(horizontalAlignment = AbsoluteAlignment.Left) {
        Text("Lil Uzi Vert - Eternal Atake", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight(400))
        Text("Prices", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight(200))
    }
}

@Composable
fun ProgressBar() {
    Box(modifier = Modifier.padding(vertical = 8.dp)) {
        Box(modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(8.dp)
            .clip(RoundedCornerShape(48.dp))
            .background(Color.Gray)
        )
        Box(modifier = Modifier
            .fillMaxWidth(0.4f)
            .height(8.dp)
            .clip(RoundedCornerShape(48.dp))
            .background(Color.Cyan)
        )
    }
}