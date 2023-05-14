package com.example.newsus.api

import com.example.newsus.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API_service {
//    2023-5-11::2023-5-10

    @GET("everything?q=apple&from=2023-5-5&to=2023-5-11&sortBy=popularity&apiKey=8ad925c3ce3a4309b73812315bbc7e97") //?country=us&category=business&apiKey=8ad925c3ce3a4309b73812315bbc7e97
    fun getDataFromAPI(): Call<News>

}