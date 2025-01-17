package com.example.abhijeet.geekNews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.abhijeet.geekNews.ViewModel.ViewModel
import com.example.abhijeet.geekNews.ui.HomePage
import com.example.abhijeet.geekNews.ui.HomePageScreen
import com.example.abhijeet.geekNews.ui.NewsArticlePage
import com.example.abhijeet.geekNews.ui.NewsUrl
import com.example.abhijeet.geekNews.ui.theme.NewsNowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = ViewModelProvider(this)[ViewModel::class.java]
        setContent {
            val navController = rememberNavController()
            NewsNowTheme {
                Scaffold(
                    modifier = Modifier._____ // Q1: Use Modifier.fillMaxSize() for a full-screen scaffold.
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(Color._____), // Q2: Set a color for the background, e.g., Color.White or Color.Black.
                    ) {
                        Spacer(modifier = Modifier.height(____.dp)) // Q3: Define a height for the spacer, e.g., 20.dp.

                        Card(
                            modifier = Modifier
                                .fillMaxWidth() // Q4: Make the card span the full width of the screen.
                                .padding(16.dp), // Q5: Add padding around the card.
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Text(
                                text = "GeekNews",
                                modifier = Modifier.align(Alignment.CenterHorizontally), // Q6: Center the text horizontally.
                                color = Color._____, // Q7: Choose text color, e.g., Color.Black or Color.White.
                                fontSize = ____.sp, // Q8: Set the font size, e.g., 24.sp.
                                fontFamily = FontFamily.Serif,
                                fontWeight = androidx.compose.ui.text.font.FontWeight._____, // Q9: Set font weight, e.g., Bold or Normal.
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp)) // Spacer to add space between elements.

                        NavHost(
                            navController = navController,
                            startDestination = HomePageScreen
                        ) {
                            composable<HomePageScreen> {
                                HomePage(
                                    viewModel,  // Q10: Pass the ViewModel instance to the HomePage composable.
                                    navController // Q11: Pass the NavController for navigation.
                                )
                            }

                            composable<NewsUrl> {
                                val args = it.toRoute<NewsUrl>() // Q12: Use `toRoute` to retrieve arguments from the route.
                                NewsArticlePage(args.url) // Q13: Pass the URL argument to the NewsArticlePage composable.
                            }
                        }
                    }
                }
            }
        }
    }
}
