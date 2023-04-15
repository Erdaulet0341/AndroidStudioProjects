package com.example.remoteshop.fragments.SellerFragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.remoteshop.R
import com.example.remoteshop.activities.Admin.ClientAdapter
import com.example.remoteshop.activities.Admin.ClientItemDetails
import com.example.remoteshop.activities.Seller.ProductAdapterSeller
import com.example.remoteshop.backend.api_instance
import com.example.remoteshop.backend.api_services
import com.example.remoteshop.backend.products.Product
import com.example.remoteshop.databinding.FragmentAllProductsSellerBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllProductsSeller : Fragment() {

    lateinit var binding: FragmentAllProductsSellerBinding
    lateinit var recyclerViewAdapter: ProductAdapterSeller

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllProductsSellerBinding.inflate(inflater)

        val id_ = activity?.intent!!.getIntExtra("id", 0)
        Log.d("era", "$id_")
        initRecyclerView()
        createData(id_)

        search(id_)

        return binding.root
    }

    private fun search(idd: Int) {
        binding.searchViewSeller.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null){
                    var filteredList = java.util.ArrayList<Product>()
                    val api = api_instance.getApiInstance().create(api_services::class.java)
                    val call = api.getSelletProducts(idd)
                    call.enqueue(object: Callback<List<Product>>{
                        override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                            var products = response.body() as ArrayList<Product>
                            products.forEach {
                                if(it.name.lowercase().contains(newText)){
                                    filteredList.add(it)
                                }
                            }
                            if(filteredList.size == 0){
                                Toast.makeText(activity, "Doest find book!", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                initRecyclerView()
                                recyclerViewAdapter.setList(filteredList)
                                recyclerViewAdapter.setOnItemClickListener(object : ProductAdapterSeller.onItemClickListener{
                                    override fun onItemClick(position: Int) {
                                        Toast.makeText(activity, "You cliked $position", Toast.LENGTH_SHORT).show()
//                        intent = Intent(this@AdminPage, ClientItemDetails::class.java)
//                        intent.putExtra("id", clients[position].id)
//                        intent.putExtra("username", clients[position].username)
//                        intent.putExtra("email", clients[position].email)
//                        intent.putExtra("city", clients[position].city)
//                        startActivity(intent)
                                    }
                                })
                                recyclerViewAdapter.notifyDataSetChanged()
                            }

                        }

                        override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                            Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
                        }

                    })
                }
                return true
            }

        })
    }

    private fun createData(idd:Int) {
        val api = api_instance.getApiInstance().create(api_services::class.java)
        Log.d("idfun", "$idd")
        val call = api.getSelletProducts(id = idd)

        call.enqueue(object: Callback<List<Product>>{
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                var products = response.body() as ArrayList<Product>
                Log.d("size", "${products.size}")
                Log.d("succ", "${response.isSuccessful}")
                Log.d("mess", "${response.message()}")

                recyclerViewAdapter.setList(products)
                recyclerViewAdapter.setOnItemClickListener(object : ProductAdapterSeller.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        Toast.makeText(activity, "You cliked $position", Toast.LENGTH_SHORT).show()
//                        intent = Intent(this@AdminPage, ClientItemDetails::class.java)
//                        intent.putExtra("id", clients[position].id)
//                        intent.putExtra("username", clients[position].username)
//                        intent.putExtra("email", clients[position].email)
//                        intent.putExtra("city", clients[position].city)
//                        startActivity(intent)
                    }
                })
                recyclerViewAdapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun initRecyclerView() {
        var recView = binding.recViewAllPr

        recView.apply {
            layoutManager = LinearLayoutManager(activity)
            recyclerViewAdapter = ProductAdapterSeller()
            adapter = recyclerViewAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AllProductsSeller()
    }
}