package com.example.newsapp.network

import com.example.newsapp.models.NewsResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET(APILinks.NEWS_LIST)
    fun getNewsAPI(): Call<NewsResponse>
}
