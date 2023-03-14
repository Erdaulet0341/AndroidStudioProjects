package com.example.bookstore.fragments


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.databinding.BookItemBinding
import com.example.bookstore.db.Books.Book
import kotlin.math.cos

class bookAdapter(val bookList:List<Book>): RecyclerView.Adapter<bookAdapter.BookHolder>() {

    class BookHolder(item:View): RecyclerView.ViewHolder(item){
        val binding = BookItemBinding.bind(item)
        fun binkBooks(book:Book) = with(binding){
            titleBook.text = book.title
            authorBook.text = book.author
            costBook.text = book.cost.toString() + " tenge"
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return BookHolder(view)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.binkBooks(bookList[position])

    }

    override fun getItemCount(): Int {
        return bookList.size
    }

//    fun addBook(book:Book){
//
//    }

}