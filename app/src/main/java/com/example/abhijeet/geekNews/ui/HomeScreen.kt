package com.example.abhijeet.geekNews.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.kwabenaberko.newsapilib.models.Article
import com.example.abhijeet.geekNews.ViewModel.ViewModel
import com.example.abhijeet.geekNews.ui.theme.NewsNowTheme

@Composable
fun HomePage(viewModel: ViewModel, navController: NavHostController) {
    val articles by viewModel.articles.observeAsState(emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        CategoriesBar(viewModel)

        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(articles) { article ->
                    ArticleItem(article, navController)
                }
            }
        }
    }
}

@Composable
fun ArticleItem(article: Article, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = {
            navController.navigate(NewsUrl(article.url))
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = article.urlToImage
                    ?: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUrgu4a7W_OM8LmAuN7Prk8dzWXm7PVB_FmA&s",
                contentDescription = "Article image",
                modifier = Modifier
                    .size(80.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
            ) {
                Text(
                    text = article.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 2,
                    color = Color.Black
                )
                Text(
                    text = article.source.name,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun CategoriesBar(viewModel: ViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearchExpanded by remember { mutableStateOf(false) }

    val categoriesList = listOf(
        "GENERAL", "BUSINESS", "ENTERTAINMENT", "HEALTH",
        "SCIENCE", "SPORTS", "TECHNOLOGY"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isSearchExpanded) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(end = 8.dp).border(1.dp, Color.White, CircleShape),
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search articles...", color = Color.White) },
                trailingIcon = {
                    IconButton(onClick = {
                        isSearchExpanded = false
                        if (searchQuery.isNotEmpty()) {
                            viewModel.fetchEverythingWithQuery(searchQuery)
                        }
                    }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                }
            )
        } else {
            IconButton(onClick = { isSearchExpanded = true }) {
                Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color.White)
            }
        }

        categoriesList.forEach { category ->
            Button(
                onClick = { viewModel.fetchNewsTopHeadlines(category) },
                modifier = Modifier.padding(horizontal = 4.dp)
                    .clip(CircleShape),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSystemInDarkTheme()) Color(0xFF03DAC6) else Color(0xFF018786),
                    contentColor = Color.Black
                )

            ) {
                Text(text = category, fontSize = 14.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DarkModePreview() {
    NewsNowTheme(darkTheme = true) {
        HomePage(ViewModel(), rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun LightModePreview() {
    NewsNowTheme(darkTheme = false) {
        HomePage(ViewModel(), rememberNavController())
    }
}