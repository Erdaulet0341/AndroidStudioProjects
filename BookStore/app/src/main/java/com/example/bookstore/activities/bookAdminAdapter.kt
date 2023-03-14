package com.example.bookstore.activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.databinding.AdminBookBinding
import com.example.bookstore.db.Books.Book
import com.example.bookstore.db.Books.bookDb
import java.util.regex.Pattern

class bookAdminAdapter(val listBook:List<Book>,val contex: Context): RecyclerView.Adapter<bookAdminAdapter.Holder>(){

    class Holder(item:View, contex: Context):RecyclerView.ViewHolder(item){
        var binding = AdminBookBinding.bind(item)
        var builder = AlertDialog.Builder(contex)
        val contextt = contex
        fun binkBooks(book: Book) = with(binding){
            titleAdmin.text = Editable.Factory.getInstance().newEditable(book.title)
            descAdmin.text = Editable.Factory.getInstance().newEditable(book.description)
            CostAmin.text = Editable.Factory.getInstance().newEditable(book.cost.toString())
        }
        fun clickLisnenerDelete(book:Book){
            binding.deleteADmin.setOnClickListener {
                val db = bookDb.getBookDb(contextt)
                val id:Int = book.bookId as Int
                builder.setTitle("Delete Book")
                    .setMessage("Are you sure delete this book? Then can not be restored!")
                    .setPositiveButton("Yes"){inter, it ->
                        Thread{
                            db.getBookDao().deleteBookById(id)
                        }.start()
                        val intent = Intent(contextt, com.example.bookstore.activities.Admin_mode::class.java)
                        contextt.startActivity(intent)
                    }
                    .setNegativeButton("No"){inter, it ->
                        inter.cancel()
                    }
                    .show()
            }
        }
        fun clickLisnenerSave(book:Book){
            binding.saveADmin.setOnClickListener {
                val db = bookDb.getBookDb(contextt)
                val id:Int = book.bookId as Int
                val num = Pattern.compile("^[0-9]+\$").toRegex()
                if(binding.titleAdmin.text.isEmpty()) binding.titleAdmin.error = "Empty title!"
                else if(binding.descAdmin.text.isEmpty()) binding.descAdmin.error = "Empty description!"
                else if(binding.CostAmin.text.isEmpty()) binding.CostAmin.error = "Empty cost!"
                else if(!binding.CostAmin.text.matches(num)) binding.CostAmin.error = "Cost must be number!"
                else if(!binding.titleAdmin.text.isEmpty()){
                    val cost:Double = binding.CostAmin.text.toString().toDouble()
                    builder.setTitle("Update Book")
                        .setMessage("Are you sure save this book changes?")
                        .setPositiveButton("Yes"){inter, it ->
                            Thread{
                                db.getBookDao().updateBook(id,
                                    binding.titleAdmin.text.toString(),
                                    binding.descAdmin.text.toString(),
                                    cost)
                            }.start()
                            val intent = Intent(contextt, com.example.bookstore.activities.Admin_mode::class.java)
                            contextt.startActivity(intent)
                        }
                        .setNegativeButton("No"){inter, it ->
                            inter.cancel()
                        }
                        .show()
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.admin_book, parent, false)
        return Holder(view,contex)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binkBooks(listBook[position])
        holder.clickLisnenerDelete(listBook[position])
        holder.clickLisnenerSave(listBook[position])
    }

    override fun getItemCount(): Int {
        return listBook.size
    }

}
