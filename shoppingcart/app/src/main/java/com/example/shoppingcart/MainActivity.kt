package com.example.shoppingcart

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.example.shoppingcart.ui.theme.ShoppingCartTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val items = listOf(
            Item("Laptop", 999.99, 1),
            Item("Wireless Mouse", 29.99, 2),
            Item("USB-C Cable", 14.99, 5)
        )
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingCartTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(modifier = Modifier.fillMaxSize(), snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) {  innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Your Cart", fontSize = 45.sp, fontWeight = FontWeight(500), modifier = Modifier.padding(20.dp))
                        items.forEach({ item ->
                            DisplayItem(item, Modifier.padding(bottom = 4.dp))
                        })
                        Spacer(modifier = Modifier.height(45.dp))
                        Summary(items, snackbarHostState)
                    }
                }
            }
        }
    }
}

data class Item(val name: String, val price: Double, val quantity: Int)

@Composable
fun DisplayItem(item: Item, modifier: Modifier) {
        Box(modifier = modifier
            .height(145.dp)
            .width(320.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(161, 191, 255))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Image(
                        painter = painterResource(when (item.name) {
                            "Laptop" -> R.drawable.laptop
                            "Wireless Mouse" -> R.drawable.mouse
                            "USB-C Cable" -> R.drawable.usb_c
                            else -> R.drawable.laptop
                        }),
                        contentDescription = "laptop",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(130.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
                Column(modifier = Modifier.padding(horizontal = 8.dp), horizontalAlignment = Alignment.End) {
                    Text("Name: ${item.name}", textAlign = TextAlign.End)
                    Text("Price: $${item.price}", textAlign = TextAlign.End)
                    Row {
                        Text("-", fontSize = 36.sp)
                        Box(
                            modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .height(50.dp)
                            .width(40.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.White),
                            contentAlignment = Alignment.Center

                        ) {
                            Text("${item.quantity}", fontSize = 30.sp)
                        }
                        Text("+", fontSize = 36.sp)
                    }
                }
            }
        }
}

@Composable
fun Summary(items: List<Item>, snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Summary", fontSize = 30.sp, fontWeight = FontWeight(400))
        Text("Total Price: $${items.map { item -> item.price }.reduce{ acc, price -> acc + price }}")

        Button(onClick = {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = "Ordered",
                    actionLabel = "Dismiss",
                    duration = SnackbarDuration.Short
                )
            }
        }) { Text("Checkout") }
    }
}