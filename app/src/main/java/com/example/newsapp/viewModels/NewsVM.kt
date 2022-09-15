package com.example.newsapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.R
import com.example.newsapp.helpers.Utils
import com.example.newsapp.listeners.RepositoryListener
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.network.APIKeys
import com.example.newsapp.repositories.NewsRepository
import com.example.newsapp.vmCallbacks.NewsCallback

class NewsVM : ViewModel(), RepositoryListener {
    private val TAG = NewsVM::class.java.simpleName

    private val newsRepository = NewsRepository(this)

    private val callback: MutableLiveData<NewsCallback> by lazy {
        MutableLiveData<NewsCallback>()
    }

    val newsResponse: MutableLiveData<NewsResponse> by lazy {
        MutableLiveData<NewsResponse>()
    }

    fun getCallback(): LiveData<NewsCallback> {
        return callback
    }

    fun getNewsApi() {
        if (Utils.isNetworkAvailable()) {
            callback.value = NewsCallback.showProgressBar(true)
            newsRepository.getCitiesApiCalling()
        }
    }

    override fun <T : Any> onSuccessResponse(key: String, result: T) {

        if (key == APIKeys.KEY_GET_NEWS_API) {
            pharmacyApiResponse(result as NewsResponse)
        }
    }

    override fun onFailureResponse(key: String, error: String) {
        callback.value = NewsCallback.showProgressBar(false)
    }

    private fun pharmacyApiResponse(newsResponse: NewsResponse) {
        callback.value = NewsCallback.showProgressBar(false)

        if (newsResponse.articles != null) {
            this.newsResponse.value = newsResponse
            callback.value = NewsCallback.PopulateAdapter
        } else {
            callback.value = NewsCallback.showToastWithResource(R.string.string_no_news_found)
        }
    }
}