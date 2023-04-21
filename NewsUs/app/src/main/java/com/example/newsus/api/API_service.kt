package com.example.newsus.api

import com.example.newsus.News
import retrofit2.Call
import retrofit2.http.GET

interface API_service {

    @GET("everything?q=apple&from=2023-04-17&to=2023-04-17&sortBy=popularity&apiKey=8ad925c3ce3a4309b73812315bbc7e97") //?country=us&category=business&apiKey=8ad925c3ce3a4309b73812315bbc7e97
    fun getDataFromAPI(): Call<News>

}