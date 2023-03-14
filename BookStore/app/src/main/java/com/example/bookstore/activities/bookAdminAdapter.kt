package com.example.bookstore.activities

import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.databinding.AdminBookBinding
import com.example.bookstore.db.Books.Book

class bookAdminAdapter(val listBook:List<Book>): RecyclerView.Adapter<bookAdminAdapter.Holder>(){


    class Holder(item:View):RecyclerView.ViewHolder(item){
        val binding = AdminBookBinding.bind(item)
        fun binkBooks(book: Book) = with(binding){
            titleAdmin.text = Editable.Factory.getInstance().newEditable(book.title)
            descAdmin.text = Editable.Factory.getInstance().newEditable(book.description)
            CostAmin.text = Editable.Factory.getInstance().newEditable(book.cost.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.admin_book, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binkBooks(listBook[position])
    }

    override fun getItemCount(): Int {
        return listBook.size
    }

}