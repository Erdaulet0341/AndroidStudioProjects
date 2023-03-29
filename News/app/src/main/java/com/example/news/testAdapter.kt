package com.example.news

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class testAdapter:RecyclerView.Adapter<testAdapter.myHolder>() {

    var lists = ArrayList<Article>()

    fun setList(l : ArrayList<Article>){
        this.lists = l
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  myHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rec_item, parent, false)
        return myHolder(v)
    }

    override fun onBindViewHolder(holder: myHolder, position: Int) {
        holder.bind(lists[position])
    }

    override fun getItemCount(): Int {
        return lists.size
    }


    class myHolder(view: View):RecyclerView.ViewHolder(view){



        val tit = view.findViewById<TextView>(R.id.text_view)
        var img = view.findViewById<ImageView>(R.id.img)


        fun bind(data: Article){
            tit.text = data.title
            val url = data.urlToImage

            Glide.with(img)
                .load(url)
                .placeholder(R.drawable.image)
                .error(R.drawable.image)
                .fallback(R.drawable.image)
                .into(img)
        }

    }
}