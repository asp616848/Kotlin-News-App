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

        Spacer(modifier = Modifier._____(10.dp)) // Q1: Spacer needs height or width based on the layout direction.
        Column(
            modifier = Modifier
                ._____(_____) // Q2: Use fillMaxWidth for horizontal layouts or fillMaxHeight for vertical.
                ._____(16.dp) // Q3: Padding applies spacing around the element.
        ) {
            LazyColumn(
                modifier = Modifier._____(_____)._____(_____()), // Q4: Use fillMaxSize or similar for layout size.
                verticalArrangement = Arrangement._____(_____.dp) // Q5: Use Arrangement.spacedBy() for spacing between items.
            ) {
                items(_____) { article -> // Q6: The items function takes a list or collection to iterate over.
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
            ._____(_____) // Q7: Cards usually span the full width of their parent.
            ._____(horizontal = 8.dp), // Q8: Padding applies space to horizontal or vertical sides.
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = {
            navController.navigate(NewsUrl(article.url))
        }
    ) {
        Row(
            modifier = Modifier
                ._____(_____) // Q9: Row usually spans full width for horizontal alignment.
                ._____(12.dp), // Q10: Padding adds space inside the container.
            verticalAlignment = Alignment._____(_____) // Q11: Aligns children vertically within the Row.
        ) {
            AsyncImage(
                model = article.urlToImage
                    ?: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUrgu4a7W_OM8LmAuN7Prk8dzWXm7PVB_FmA&s",
                contentDescription = "Article image",
                modifier = Modifier
                    ._____(80.dp) // Q12: Use size to define width and height of an element.
                    ._____(1f) // Q13: Aspect ratio of 1f keeps the element square.
                    ._____(CircleShape) // Q14: Clip to shape, e.g., CircleShape.
                    ._____(1.dp, Color.Gray, CircleShape), // Q15: Add border with thickness, color, and shape.
                contentScale = ContentScale._____(_____) // Q16: Crop scales image to fill container.
            )

            Column(
                modifier = Modifier
                    ._____(start = 12.dp) // Q17: Padding on start for space between image and text.
                    ._____(1f) // Q18: Weight ensures text column takes up remaining space.
            ) {
                Text(
                    text = article.title,
                    fontWeight = FontWeight._____, // Q19: FontWeight controls text weight (e.g., Bold).
                    fontSize = _____.sp, // Q20: FontSize is measured in sp for scalable pixels.
                    maxLines = 2,
                    color = Color._____(_____) // Q21: Text color is defined by Color object.
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
            ._____(_____) // Q22: Fill the full width for horizontal alignment.
            ._____(8.dp) // Q23: Add padding for spacing around the row.
            ._____(rememberScrollState()), // Q24: Horizontal scroll for overflowing elements.
        verticalAlignment = Alignment._____(_____) // Q25: Vertically aligns items within the Row.
    ) {
        if (isSearchExpanded) {
            OutlinedTextField(
                modifier = Modifier
                    ._____(end = 8.dp) // Q26: Add padding to separate from other elements.
                    ._____(1.dp, Color.White, CircleShape), // Q27: Add border with a circular shape.
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search articles...", color = Color.White) }, // Q28: Placeholder for text input.
                trailingIcon = {
                    IconButton(onClick = {
                        isSearchExpanded = false
                        if (searchQuery.isNotEmpty()) {
                            viewModel.fetchEverythingWithQuery(searchQuery)
                        }
                    }) {
                        Icon(Icons.Default.Search, contentDescription = "Search") // Q29: Icon with description for accessibility.
                    }
                }
            )
        } else {
            IconButton(onClick = { isSearchExpanded = true }) {
                Icon(Icons.Filled.Search, contentDescription = "__Add a description of your choice___", tint = ____) // Color.White or Color.Black or whatever
            }
        }

        categoriesList.forEach { category ->
            Button(
                onClick = { viewModel.fetchNewsTopHeadlines(category) },
                modifier = Modifier._____(horizontal = 4.dp) // Q30: Horizontal padding for button spacing.
                    ._____(CircleShape), // Q31: Clip button shape to a circle.
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSystemInDarkTheme()) Color(0xFF03DAC6) else Color(0xFF018786),
                    contentColor = Color.Black
                )
            ) {
                Text(text = category, fontSize = 14.sp) // Q32: Text size and content within the button.
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