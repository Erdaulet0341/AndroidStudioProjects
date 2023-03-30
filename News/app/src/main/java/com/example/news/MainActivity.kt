package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news.Api.apiInstance
import com.example.news.Api.apiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recV: testAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        createData()
    }

    private fun initRecyclerView(){
        var rec = findViewById<RecyclerView>(R.id.recycler)
        rec.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recV = testAdapter()
            adapter = recV

            addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))
        }
    }

    fun createData(){

        val api = apiInstance.getApiInstance().create(apiInterface::class.java)
        val call = api.getDataFromAPI()
        Log.d("crete date", "true")
        call.enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                Log.d("call", "${call}")
                    if(response.isSuccessful){
                        recV.setList(response.body()?.articles!!)
                        recV.notifyDataSetChanged()
                        Log.d("isSuccessful", "true")
                }
                if(!response.isSuccessful){
                    Log.d("Fial", "true")
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                Log.d("onFailure", "true")
            }

        })
    }

}