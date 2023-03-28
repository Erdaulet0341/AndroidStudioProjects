package com.example.bookstore.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Query
import com.example.bookstore.databinding.FragmentShowBooksBinding
import com.example.bookstore.db.Books.Book
import com.example.bookstore.db.Books.bookDb
import java.util.*

class showBooks : Fragment() {
    lateinit var binding:FragmentShowBooksBinding
    lateinit var listBooks: List<Book>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowBooksBinding.inflate(inflater)

        val context = requireContext()

        val bookDb = bookDb.getBookDb(context)

        bookDb.getBookDao().getAllBooks().asLiveData().observe(viewLifecycleOwner){
            listBooks = it
            var adapter= bookAdapter(listBooks)
            binding.recyclerFragment.layoutManager = LinearLayoutManager(activity)
            binding.recyclerFragment.adapter = adapter
        }

        binding.desc.setOnClickListener {
            bookDb.getBookDao().getAllBiiksByCostDesc().asLiveData().observe(viewLifecycleOwner){
                listBooks = it
                var adapter= bookAdapter(listBooks)
                binding.recyclerFragment.layoutManager = LinearLayoutManager(activity)
                binding.recyclerFragment.adapter = adapter
                binding.searchView.setQuery("", false)
            }
        }

        binding.asc.setOnClickListener {
            bookDb.getBookDao().getAllBiiksByCostAsc().asLiveData().observe(viewLifecycleOwner){
                listBooks = it
                var adapter= bookAdapter(listBooks)
                binding.recyclerFragment.layoutManager = LinearLayoutManager(activity)
                binding.recyclerFragment.adapter = adapter
            }
            binding.searchView.setQuery("", false)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null){
                    var filteredList = ArrayList<Book>()
                    bookDb.getBookDao().getAllBooks().asLiveData().observe(viewLifecycleOwner){list ->
                        list.forEach{
                            if(it.title.lowercase(Locale.ROOT).contains(newText)){
                                Log.d("FilterTitle", it.title)
                                filteredList.add(it)
                            }
                        }
                        if(filteredList.size == 0){
                            Toast.makeText(activity, "Doest find book!", Toast.LENGTH_SHORT).show()
                            var adapter= bookAdapter(filteredList)
                            binding.recyclerFragment.layoutManager = LinearLayoutManager(activity)
                            binding.recyclerFragment.adapter = adapter
                        }
                        else{
                            var adapter= bookAdapter(filteredList)
                            binding.recyclerFragment.layoutManager = LinearLayoutManager(activity)
                            binding.recyclerFragment.adapter = adapter
                        }
                    }
                }
                return true
            }

        })

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = showBooks()
    }
}