package com.example.abhijeet.geekNews.ui

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun NewsArticlePage(url: String) {
    AndroidView(factory = { context ->
        WebView(context).apply { // Extra: Read about WebView
            settings.javaScriptEnabled = ____ // we would need JS to load the page
                //true? false?
            webViewClient = WebViewClient()
            loadUrl( ___address___ ) // look at all variables and parameters you have
        }
    })
}