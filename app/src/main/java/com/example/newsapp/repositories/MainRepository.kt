package com.example.newsapp.repositories

import com.example.newsapp.listeners.RepositoryListener
import com.example.newsapp.network.APIKeys
import com.example.newsapp.network.RetrofitCalling
import com.example.newsapp.network.RetrofitController
import com.example.newsapp.repositories.base.BaseRepository

class MainRepository(repositoryListener: RepositoryListener) :
    BaseRepository(repositoryListener) {

    fun getCitiesApiCalling() {
        val apiService = RetrofitController.apiService.getNewsAPI()
        val retrofitCalling = RetrofitCalling(this, APIKeys.KEY_GET_CITY_API, apiService)
        retrofitCalling.apiCalling()
    }
}