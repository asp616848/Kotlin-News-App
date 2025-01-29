// package com.example.myapplication YOUR PACKAGE PATH HERE

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// Main screen with multiple composables
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {  // Ensures bounded constraints
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            item { GreetingComposable() } // Single header item
            item { InputComposable() } // TextField
            item { ScrollableRowExample() } // Horizontal scrolling
            items(10) { index ->  // LazyColumn List items
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Cyan)
                ) {
                    Text(text = "List Item ${index + 1}", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

// Simple Greeting Text
@Composable
fun GreetingComposable() {
    Text(text = "Hello, Welcome to Android Development!", style = MaterialTheme.typography.headlineMedium)
}

// TextField with state management
@Composable
fun InputComposable() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter your name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = if (text.text.isNotEmpty()) "Hello, ${text.text}!" else "Type something...")
    }
}

// Horizontal scrolling row
@Composable
fun ScrollableRowExample() {
    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    Row(modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState())
    ) {
        items.forEach { item ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .width(100.dp),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray)
            ) {
                Text(text = item, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

// Preview for quick UI testing
@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MyApplicationTheme {
        MainScreen()
    }
}
