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
    val articles: LiveData<List<Article>> = ______
    // what would you like your local variable to be set to upon value changes and initialization

    init {
        fetchNewsTopHeadlines()
    }

    fun fetchNewsTopHeadlines(category : String = "GENERAL"){
        val newsApiClient = NewsApiClient(____) //TODO : Add your API key here, use the object you created
        //  {Object_name.variable_name}

        val request = TopHeadlinesRequest.Builder().language("en").category(category).build()

        _______.getTopHeadlines(request, object : NewsApiClient.ArticlesResponseCallback{
            // we call api functions on object/reference of client of that API. What is the client var here?

            // onSuccess and onFailure are the two functions that we need to override to choose what happens
            // when the API call is successful or fails

            override fun ______(response: ArticleResponse?) {
                response?.articles?.let {
                    _articles.postValue(it)
                }
            }
            override fun _______(throwable: Throwable?) {
                if (throwable != null) {
                    Log.i("NewsAPI Response Failed",throwable.localizedMessage)
                }
            }

        })

    }

    fun fetchEverythingWithQuery(query : String){
        val newsApiClient = NewsApiClient(______)//TODO : Add your API key here, use the object you created
        //  {Object_name.variable_name}

        val request = EverythingRequest.Builder().language("en").q(query).build()

        ________.getEverything(request, object : NewsApiClient.ArticlesResponseCallback{
            // we call api functions on object/reference of client of that API. What is the client var here?



            // onSuccess and onFailure are the two functions that we need to override to choose what happens
            // when the API call is successful or fails
            override fun ______(response: ArticleResponse?) {
                response?.articles?.let {
                    _articles.postValue(it)
                }
            }

            override fun _______(throwable: Throwable?) {
                if (throwable != null) {
                    Log.i("NewsAPI Response Failed",throwable.localizedMessage)
                }
            }

        })

    }

}









