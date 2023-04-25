package com.example.remoteshop.fragments.ClientFragments

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.remoteshop.R
import com.example.remoteshop.backend.api_instance
import com.example.remoteshop.backend.api_services
import com.example.remoteshop.backend.products.Product
import com.example.remoteshop.databinding.FragmentProductDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Product_details : Fragment() {

    lateinit var binding: FragmentProductDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater)

        val args = arguments
        val idProduct = args?.getString("id")?.toInt()!!
        val idSeller = activity?.intent!!.getIntExtra("id", 0)

        createPage(idProduct)

        return binding.root
    }

    private fun createPage(productID: Int) {
        val retrofit = api_instance.getApiInstance()
        val service = retrofit.create(api_services::class.java)
        val call = service.getProductById(productID)

        var product: Product

        call.enqueue(object: Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                product = response.body()!!
                binding.nameDet.text = product.name
                binding.desDet.text = product.description
                binding.priceDet.text = product.price.toString()
                binding.quantutyDet.text = product.quantity.toString()
                binding.companuNameDet.text = "Kaspi.kz"

                (activity as AppCompatActivity).supportActionBar?.title = "${product.name} details"


                val img = binding.imgDet
                val url = product.imageURL
                Glide.with(img)
                    .load(url)
                    .placeholder(R.drawable.dont_image_24)
                    .error(R.drawable.dont_image_24)
                    .fallback(R.drawable.dont_image_24)
                    .into(img)

            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = Product_details()
    }
}