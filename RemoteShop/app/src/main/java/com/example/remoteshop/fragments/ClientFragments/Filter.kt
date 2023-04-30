package com.example.remoteshop.fragments.ClientFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.remoteshop.R
import com.example.remoteshop.backend.api_instance
import com.example.remoteshop.backend.api_services
import com.example.remoteshop.backend.products.Category
import com.example.remoteshop.backend.users.Client
import com.example.remoteshop.backend.users.Seller
import com.example.remoteshop.databinding.FragmentFilterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class Filter : Fragment() {

    lateinit var binding: FragmentFilterBinding
    val num = Pattern.compile("^[0-9]+\$").toRegex()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilterBinding.inflate(inflater)

        var listOfCategories = AllCategoryNames()
        var listOfCompanies = AllCompanyNames()
        var listOfRating = arrayListOf(1,2,3,4,5)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_category, listOfCategories)
        binding.autoCategory.setAdapter(arrayAdapter)
        val arrayAdapter2 = ArrayAdapter(requireContext(), R.layout.drop_down_category, listOfCompanies)
        binding.autoCompany.setAdapter(arrayAdapter2)
        val arrayAdapter3 = ArrayAdapter(requireContext(), R.layout.drop_down_category, listOfRating)
        binding.autoRatingFrom.setAdapter(arrayAdapter3)
        binding.autoRatingTo.setAdapter(arrayAdapter3)

        binding.applyBtn.setOnClickListener {
            filteringFun()
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    fragmentManager?.beginTransaction()?.replace(R.id.fragment_client_page, ListOfAllProducts.newInstance())?.commit()
                    (activity as AppCompatActivity).supportActionBar?.title = "All Products"
                }
            }
        )
        return binding.root
    }

    private fun filteringFun() {
        var category = binding.autoCategory
        var company = binding.autoCompany
        var ratingFrom = binding.autoRatingFrom
        var ratingTo = binding.autoRatingTo
        var priceFrom = binding.priceFrom
        var priceTo = binding.priceTo

        var categoryText = "no"
        var companyText = "no"
        var ratingFromText = "0"
        var ratingToText = "0"
        var priceFromText = "0"
        var priceToText = "500000000"



        if(category.text.toString().isNotEmpty()) categoryText = category.text.toString()
        if(company.text.toString().isNotEmpty()) companyText = company.text.toString()

        if(ratingFrom.text.toString().isEmpty()) ratingFromText = "0"
        else ratingFromText = ratingFrom.text.toString()
        if(ratingTo.text.toString().isEmpty()) ratingToText = "5"
        else ratingToText = ratingTo.text.toString()

        if(priceTo.text.toString().isEmpty()) priceToText = "500000000"
        else{
            if(!priceTo.text.toString().matches(num)){
                priceFrom.error = "Price From must be number"
            }
            else{
                priceToText = priceTo.text.toString().toInt().toString()
            }
        }

        if(priceFrom.text.toString().isEmpty()) priceFromText = "0"
        else{
            if(!priceFrom.text.toString().matches(num)){
                priceFrom.error = "Price to must be number"
            }
            else{
                priceFromText = priceFrom.text.toString().toInt().toString()
            }
        }

        if(ratingFromText.toInt() >= ratingToText.toInt()){
            ratingFrom.error = "Rating From must be small Rating To"
        }
        else if(priceFromText.toInt() >= priceToText.toInt()){
            priceFrom.error = "Price From must be small Price To"
        }
        else{
            val bundle = Bundle()
            bundle.putString("category", categoryText)
            bundle.putString("company", companyText)
            bundle.putString("ratingTo", ratingToText)
            bundle.putString("ratingFrom", ratingFromText)
            bundle.putString("priceFrom", priceFromText)
            bundle.putString("priceTo", priceToText)

            val fragment = FilteredProducts()
            fragment.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(R.id.fragment_client_page, fragment)?.commit()
        }
    }


    private fun AllCategoryNames(): ArrayList<String>{
        val api = api_instance.getApiInstance().create(api_services::class.java)
        val call = api.getAllCategories()
        var list = ArrayList<String>()
        call.enqueue(object: Callback<List<Category>> {
            override fun onResponse(
                call: Call<List<Category>>,
                response: Response<List<Category>>
            ) {
                var categories = response?.body()!!

                categories.forEach {
                    list.add(it.name)
                }
            }

            override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

        return list
    }

    private fun AllCompanyNames(): ArrayList<String>{
        val api = api_instance.getApiInstance().create(api_services::class.java)
        val call = api.getAllSellers()
        var list = ArrayList<String>()
        call.enqueue(object: Callback<List<Seller>>{
            override fun onResponse(
                call: Call<List<Seller>>,
                response: Response<List<Seller>>
            ) {
                var categories = response?.body()!!

                categories.forEach {
                    list.add(it.company_name)
                }
            }

            override fun onFailure(call: Call<List<Seller>>, t: Throwable) {
                Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

        return list
    }

    companion object {
        @JvmStatic
        fun newInstance() = Filter()
    }
}