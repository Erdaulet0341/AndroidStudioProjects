package com.example.bookstore.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentShowBooksBinding
import com.example.bookstore.db.Books.Book
import com.example.bookstore.db.Books.bookDb

class showBooks : Fragment() {
    lateinit var binding:FragmentShowBooksBinding
    lateinit var listBooks: List<Book>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowBooksBinding.inflate(inflater)

        val context = requireContext()

        val bookDb = bookDb.getBookDb(context)
        bookDb.getBookDao().getAllBooks().asLiveData().observe(viewLifecycleOwner){
            listBooks = it
            var adapter= bookAdapter(listBooks)
            binding.recyclerFragment.layoutManager = LinearLayoutManager(activity)
            binding.recyclerFragment.adapter = adapter
        }

//        var adapter= bookAdapter(listBooks)

//        binding.recyclerFragment.apply {
//            binding.recyclerFragment.layoutManager = LinearLayoutManager(activity)
//        }


        return binding.root
    }



    companion object {
        @JvmStatic
        fun newInstance() = showBooks()
    }
}