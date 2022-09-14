package com.example.newsapp.listeners

import retrofit2.Response

interface RetrofitCallingListener {

    fun <T : Any> onSuccessResponse(key: String, response: Response<T>?)
    fun onFailureResponse(key: String, error: String)
}