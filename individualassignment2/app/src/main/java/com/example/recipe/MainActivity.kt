package com.example.recipe

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.recipe.ui.theme.RecipeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val recipe = Recipe(
            name = "Green Eggs and Ham",
            ingredients = listOf(
                "Ham",
                "Eggs",
                "Green food coloring"
            ),
            instructions = listOf(
                "Crack the eggs",
                "Whisk them",
                "Add food coloring",
                "Cook them",
                "Slice ham"
            )
        )
        setContent {
            Recipe(recipe)
        }
    }
}

data class Recipe(val name: String, val ingredients: List<String>, val instructions: List<String>)

@Composable
fun Recipe(recipe: Recipe) {
    Column(modifier = Modifier.fillMaxSize().background(color = Color.Green.copy(alpha = 0.5f)).padding(16.dp)) {
        RecipeTitle(title=recipe.name)
        RecipeImage(modifier=Modifier)
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        RecipeIngredients(ingredients=recipe.ingredients)
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        RecipeInstructions(instructions = recipe.instructions)
    }
}

@Composable
fun RecipeTitle(title: String) {
    Text(title, fontSize = 40.sp, fontWeight = FontWeight(600), modifier = Modifier.padding(top=16.dp))
}

@Composable
fun RecipeImage(modifier: Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        Box(modifier = Modifier
            .size(266.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.Gray.copy(alpha = 0.2f))
        )
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.greeneggs),
                contentDescription = "A picture of green eggs ans ham",
                modifier = Modifier
                    .size(256.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun RecipeIngredients(ingredients: List<String>) {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
        containerColor = Color.Gray.copy(alpha = 0.2f)
    )) {
        Column(modifier = Modifier.padding(8.dp)) {
            RecipeTitle("Ingredients")
            ingredients.map { ingredient ->
                Text("- $ingredient", fontSize = 24.sp)
            }
        }
    }
}

@Composable
fun RecipeInstructions(instructions: List<String>) {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
        containerColor = Color.Gray.copy(alpha = 0.2f)
    )) {
        Column(modifier = Modifier.padding(8.dp)) {
            RecipeTitle("Instructions")
            instructions.mapIndexed { index, instruction ->
                Text("${index+1}. $instruction", fontSize = 24.sp)
            }
        }
    }
}