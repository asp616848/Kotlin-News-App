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
    val articles: LiveData<List<Article>> = _articles


    init {
        fetchNewsTopHeadlines()
    }

    fun fetchNewsTopHeadlines(category : String = "GENERAL"){
        val newsApiClient = NewsApiClient(API.apiKey)

        val request = TopHeadlinesRequest.Builder().language("en").category(category).build()

        newsApiClient.getTopHeadlines(request, object : NewsApiClient.ArticlesResponseCallback{
            override fun onSuccess(response: ArticleResponse?) {
                response?.articles?.let {
                    _articles.postValue(it)
                }
            }
            override fun onFailure(throwable: Throwable?) {
                if (throwable != null) {
                    Log.i("NewsAPI Response Failed",throwable.localizedMessage)
                }
            }

        })

    }

    fun fetchEverythingWithQuery(query : String){
        val newsApiClient = NewsApiClient(API.apiKey)

        val request = EverythingRequest.Builder().language("en").q(query).build()

        newsApiClient.getEverything(request, object : NewsApiClient.ArticlesResponseCallback{
            override fun onSuccess(response: ArticleResponse?) {
                response?.articles?.let {
                    _articles.postValue(it)
                }
            }

            override fun onFailure(throwable: Throwable?) {
                if (throwable != null) {
                    Log.i("NewsAPI Response Failed",throwable.localizedMessage)
                }
            }

        })

    }

}









