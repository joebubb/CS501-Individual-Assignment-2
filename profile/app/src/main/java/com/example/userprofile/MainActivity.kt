package com.example.userprofile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.userprofile.ui.theme.UserProfileTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserProfileTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().fillMaxHeight(), verticalArrangement = Arrangement.SpaceAround) {
                        ProfilePicture()
                        Column {
                            Username()
                            Spacer(modifier = Modifier.height(4.dp))
                            Bio()
                        }
                        FollowButton(snackbarHostState=snackbarHostState)
                    }
                }
            }
        }
    }
}

@Composable
fun ProfilePicture() {
    Image(
        painter = painterResource(R.drawable.face),
        contentDescription = "A woman's face",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(top = 8.dp)
            .size(300.dp)
            .clip(CircleShape)
    )
}

@Composable
fun Username() {
    Column(modifier = Modifier.width(300.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Username", fontSize = 30.sp, fontWeight = FontWeight(500))
        Text("rosewater", fontSize = 20.sp, fontWeight = FontWeight(300))
    }
}

@Composable
fun Bio() {
    Column(modifier = Modifier.width(300.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Bio", fontSize = 30.sp, fontWeight = FontWeight(500))
        Text("herbalist & tea enthusiast. probably reading in a garden somewhere. digital art commissions open.", fontSize = 20.sp, fontWeight = FontWeight(300), textAlign = TextAlign.Center)
    }
}

@Composable
fun FollowButton(snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    Box(contentAlignment = Alignment.Center, modifier = Modifier
            .clickable {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Following",
                        actionLabel = "Dismiss",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        ) {
            Box(modifier = Modifier
                .height(50.dp)
                .width(150.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Blue)
            )

            Text("Follow", fontSize = 24.sp, fontWeight = FontWeight(400), color = Color.White)
        }
}