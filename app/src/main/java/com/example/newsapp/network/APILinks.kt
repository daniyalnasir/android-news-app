package com.example.newsapp.network

import com.example.newsapp.helpers.Constants


object APILinks {

    /* BaseURL */
    const val BASE_URL: String = "https://newsapi.org/v2/"

    private const val API_KEY: String = "32bda660df684ace823fbc58e7f7c9ae"

    /* EndPoints */
    const val NEWS_LIST: String = "everything?q=bitcoin&apiKey=${API_KEY}"
}