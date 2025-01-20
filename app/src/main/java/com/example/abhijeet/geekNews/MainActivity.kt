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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
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
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = ViewModelProvider(this)[ViewModel::class.java]
        setContent {
            val navController = rememberNavController()
            NewsNowTheme {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        MediumTopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(
                                    "GeekNews"
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Localized description"
                                    )
                                }
                            },
                            scrollBehavior = scrollBehavior,
                        )
                    },
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(Color.Black),
                    ) {

                        Spacer(modifier = Modifier.height(10.dp))
                        Card(modifier = Modifier.fillMaxWidth(), shape = MaterialTheme.shapes.medium) {
                            Text(
                                text = "GeekNews",
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                color = Color.Black,
                                fontSize = 25.sp,
                                fontFamily = FontFamily.Serif,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        NavHost(navController = navController, startDestination = HomePageScreen) {
                            composable<HomePageScreen> {
                                HomePage(viewModel, navController)
                            }

                            composable<NewsUrl> {
                                val args = it.toRoute<NewsUrl>()  // this  returns the NewsUrl object from data class in Route.kt
                                NewsArticlePage(args.url)  // pass obtained url to composable
                            }
                        }
                    }
                }
            }
        }
    }
}