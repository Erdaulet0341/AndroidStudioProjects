package com.example.remoteshop.backend

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class api_instance {
    companion object {
        val url = "http://192.168.80.115:4000/api/"  //https://android.pythonanywhere.com/api/
        fun getApiInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(url)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }
}