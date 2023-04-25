package com.example.newsus.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsus.News
import com.example.newsus.api.API_instance
import com.example.newsus.api.API_service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelNews: ViewModel() {
    private var movieLiveData = MutableLiveData<News>()
    fun getAllNews() {
        val api = API_instance.getApiInstance().create(API_service::class.java)
        val call = api.getDataFromAPI()
        call.enqueue(object :
            Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.body()!=null){
                    movieLiveData.value = response.body()!!
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("error", t.message.toString())
            }
        })
    }
    fun observeMovieLiveData() : LiveData<News> {
        return movieLiveData
    }
}
