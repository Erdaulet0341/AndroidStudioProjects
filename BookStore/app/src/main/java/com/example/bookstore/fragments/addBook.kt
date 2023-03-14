package com.example.bookstore.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentAddBookBinding
import com.example.bookstore.db.Books.Book
import com.example.bookstore.db.Books.bookDb
import java.util.regex.Pattern

class addBook : Fragment() {

    lateinit var binding: FragmentAddBookBinding
    val num = Pattern.compile("^[0-9]+\$").toRegex()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBookBinding.inflate(inflater)
         val contest = requireActivity()

        val db = bookDb.getBookDb(contest)

        binding.addNewBook.setOnClickListener {
            if(binding.titleAdd.text.isEmpty()) binding.titleAdd.error = "Empty title!"
            else if(binding.authorAdd.text.isEmpty()) binding.authorAdd.error = "Empty author!"
            else if(binding.descAdd.text.isEmpty()) binding.descAdd.error = "Empty description!"
            else if(binding.costAdd.text.isEmpty()) binding.costAdd.error = "Empty cost!"
            else if(!binding.costAdd.text.matches(num)) binding.costAdd.error = "Cost must be number!"
            else{
                var temp = Book(
                    null,
                    binding.titleAdd.text.toString(),
                    binding.authorAdd.text.toString(),
                    binding.descAdd.text.toString(),
                    binding.costAdd.text.toString().toDouble(),
                )
                Thread{
                    db.getBookDao().insertBook(temp)
                }.start()
                Toast.makeText(contest, "Book succesfully added!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.button2.setOnClickListener {
            val intent = Intent(activity, com.example.bookstore.activities.Admin_mode::class.java)
            startActivity(intent)
        }


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = addBook()
    }
}