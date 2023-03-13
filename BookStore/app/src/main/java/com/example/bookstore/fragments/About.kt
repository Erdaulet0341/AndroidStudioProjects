package com.example.bookstore.fragments

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import com.example.bookstore.R
import com.example.bookstore.activities.MainActivity
import com.example.bookstore.db.bookUsers.userDb

class About : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val db = userDb.getUserDb(context)
        db.getDao().getAllUser().asLiveData().observe(this){
            Log.i(ContentValues.TAG, "${it[0].email}")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = About()
    }
}