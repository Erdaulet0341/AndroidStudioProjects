package com.example.newsus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsus.api.API_instance
import com.example.newsus.api.API_service
import com.example.newsus.databinding.ActivityMainBinding
import com.example.newsus.mvvm.ViewModelNews
import com.example.newsus.savedItems.SavedNews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var recyclerViewAdapter: NewsAdapter
    var backPressedTime: Long = 0
    lateinit var builder: AlertDialog.Builder
    private lateinit var viewModel: ViewModelNews

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        builder = AlertDialog.Builder(this)
        viewModel = ViewModelProvider(this)[ViewModelNews::class.java]
        setContentView(binding.root)

        supportActionBar?.title = "All News"

        bottomBar()
        left_menu()
        initRecyclerView()
        createData()
    }

    private fun createData() {

        val api = API_instance.getApiInstance().create(API_service::class.java)
        val call = api.getDataFromAPI()
        viewModel.getAllNews()
        viewModel.observeMovieLiveData().observe(this, Observer { news ->
            recyclerViewAdapter.setList(news.articles)
        })

        call.enqueue(object : Callback<News>{

            override fun onResponse(call: Call<News>, response: Response<News>) {
                if(response.isSuccessful){
                    val listNews = response.body()?.articles!!
                    listNews.forEachIndexed { index, article ->
                        if(article.urlToImage == null){
                            article.urlToImage = "https://st3.depositphotos.com/23594922/31822/v/600/depositphotos_318221368-stock-illustration-missing-picture-page-for-website.jpg"
                        }
                        if(article.title == null){
                            article.title = "No title"
                        }
                        if(article.description == null){
                            article.description = "No description"
                        }
                        if(article.content == null){
                            article.content = "No content"
                        }
                        if(article.publishedAt == null){
                            article.publishedAt = "No time  , no data"
                        }
                        if(article.author == null){
                            article.author = "No author"
                        }
                        if(article.url == null){
                            article.url = "No url"
                        }
                        if(article.source.name == null){
                            article.source.name = "No name"
                        }
                    }
                    recyclerViewAdapter.setList(listNews)
                    recyclerViewAdapter.setOnItemClickListener(object : NewsAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
//                            Toast.makeText(this@MainActivity, "You cliked $position", Toast.LENGTH_SHORT).show()
                            intent = Intent(this@MainActivity, ItemDetails::class.java)
                            intent.putExtra("title", listNews[position].title)
                            intent.putExtra("description", listNews[position].description)
                            intent.putExtra("author", listNews[position].author)
                            intent.putExtra("content", listNews[position].content)
                            intent.putExtra("imgUrl", listNews[position].urlToImage)
                            intent.putExtra("url", listNews[position].url)
                            intent.putExtra("time", listNews[position].publishedAt)
                            intent.putExtra("sourceName", listNews[position].source.name)
                            intent.putExtra("sourceId", listNews[position].source.id)

                            startActivity(intent)
                        }
                    })
                    recyclerViewAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(this@MainActivity, "No internet access try again!", Toast.LENGTH_SHORT).show()
                Log.d("onFailure", "true")
            }

        })

    }

    private fun initRecyclerView() {
        var recyclerView  = binding.recyclerView

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = NewsAdapter()
            adapter = recyclerViewAdapter
        }
    }



    private fun left_menu() {
        binding.leftMenu.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.saved -> {
                    var int = Intent(this, SavedNews::class.java)
                    startActivity(int)
                }
            }

            true
        }
    }

    private fun bottomBar() {
        binding.bottomAppBar.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.menu -> binding.drawer.openDrawer(GravityCompat.START)
                R.id.news -> Toast.makeText(this, "News", Toast.LENGTH_SHORT).show()
            }

            true
        }
    }

    override fun onBackPressed() {
        if (backPressedTime + 10 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            builder.setTitle("Close application")
                .setMessage("Do you want to close application?")
                .setPositiveButton("Yes"){id, it ->
                    finish()
                }
                .setNegativeButton("No"){id, it ->
                    id.cancel()
                }
                .show()
        }
        backPressedTime = System.currentTimeMillis()
    }

}