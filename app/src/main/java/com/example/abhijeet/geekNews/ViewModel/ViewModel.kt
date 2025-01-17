package com.example.abhijeet.geekNews.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse
import com.example.abhijeet.geekNews.API

class ViewModel : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _____ // Q1: Use `_articles` to link LiveData.

    init {
        fetchNewsTopHeadlines()
    }

    fun fetchNewsTopHeadlines(category: String = "GENERAL") {
        val newsApiClient = NewsApiClient(_____) // Q2: Provide your API key using `API.KEY`.

        val request = TopHeadlinesRequest.Builder()
            .language("en")
            .category(category)
            .build()

        newsApiClient.getTopHeadlines(request, object : NewsApiClient.ArticlesResponseCallback {
            // onSuccess and onFailure are the two functions that we need to override.

            override fun onSuccess(response: ArticleResponse?) { // Q3: Override the `onSuccess` function.
                response?.articles?.let {
                    _articles.postValue(it) // Update the LiveData with the fetched articles.
                }
            }

            override fun onFailure(throwable: Throwable?) { // Q4: Override the `onFailure` function.
                if (throwable != null) {
                    Log.i("NewsAPI Response Failed", throwable.localizedMessage ?: "Error") // Log the error message.
                }
            }
        })
    }

    fun fetchEverythingWithQuery(query: String) {
        val newsApiClient = NewsApiClient(_____) // Q5: Provide your API key using `API.KEY`.

        val request = EverythingRequest.Builder()
            .language("en")
            .q(query)
            .build()

        newsApiClient.getEverything(request, object : NewsApiClient.ArticlesResponseCallback {
            // onSuccess and onFailure are the two functions that we need to override.

            override fun onSuccess(response: ArticleResponse?) { // Q6: Override the `onSuccess` function.
                response?.articles?.let {
                    _articles.postValue(it) // Update the LiveData with the fetched articles.
                }
            }

            override fun onFailure(throwable: Throwable?) { // Q7: Override the `onFailure` function.
                if (throwable != null) {
                    Log.i("NewsAPI Response Failed", throwable.localizedMessage ?: "Error") // Log the error message.
                }
            }
        })
    }
}
