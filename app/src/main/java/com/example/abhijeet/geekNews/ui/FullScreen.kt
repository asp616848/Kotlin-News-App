package com.example.abhijeet.geekNews.ui

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun NewsArticlePage(url: String) {
    AndroidView(factory = { context ->
        WebView(context).apply { // Extra: Read about WebView
            settings.javaScriptEnabled = true // we would need JS to load the page
                //true? false?
            webViewClient = WebViewClient()
            loadUrl( url ) // look at all variables and parameters you have
        }
    })
}