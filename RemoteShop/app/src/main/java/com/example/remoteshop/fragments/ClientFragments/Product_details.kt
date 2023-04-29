package com.example.remoteshop.fragments.ClientFragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.remoteshop.R
import com.example.remoteshop.backend.api_instance
import com.example.remoteshop.backend.api_services
import com.example.remoteshop.backend.products.Product
import com.example.remoteshop.backend.products.Rating
import com.example.remoteshop.backend.users.Seller
import com.example.remoteshop.databinding.FragmentProductDetailsBinding
import com.example.remoteshop.fragments.ClientFragments.products_adapters.ProductsAdapterClient
import com.example.remoteshop.fragments.ClientFragments.products_adapters.RatingAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.RoundingMode

class Product_details : Fragment() {

    lateinit var binding: FragmentProductDetailsBinding
    lateinit var ratingAdapter: RatingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater)

        val args = arguments
        val idProduct = args?.getString("id")?.toInt()!!
        val idClient = activity?.intent!!.getIntExtra("id", 0)

        createPage(idProduct)

        setupRating()
        fillRating(idProduct)

        binding.addCommetBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("idProduct", "$idProduct")
            bundle.putString("idClient", "$idClient")
            val fragment = AddComment()
            fragment.arguments = bundle

            fragmentManager?.beginTransaction()?.replace(R.id.fragment_client_page, fragment)?.commit()
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    fragmentManager?.beginTransaction()?.replace(R.id.fragment_client_page, HomePage.newInstance())?.commit()
                    (activity as AppCompatActivity).supportActionBar?.title = "Home page"
                }
            }
        )

        return binding.root
    }

    private fun fillRating(idProduct: Int) {
        val api = api_instance.getApiInstance().create(api_services::class.java)
        val call = api.getProductRating(idProduct)

        call.enqueue(object: Callback<List<Rating>> {
            override fun onResponse(call: Call<List<Rating>>, response: Response<List<Rating>>) {
                var ratings = response.body() as ArrayList<Rating>
                if(ratings.size == 0) binding.noCommet.text = "NO COMMENT YET"
                ratingAdapter.setList(ratings)
                ratingAdapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<List<Rating>>, t: Throwable) {
                Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setupRating() {
        binding.commentsRec.apply {
            layoutManager  = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            ratingAdapter = RatingAdapter()
            adapter = ratingAdapter
        }
    }

    private fun createPage(productID: Int) {
        val retrofit = api_instance.getApiInstance()
        val service = retrofit.create(api_services::class.java)
        val productsCall = service.getProductById(productID)
        val ratingCall = service.getProductRating(productID)

        var product: Product

        productsCall.enqueue(object: Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                product = response.body()!!
                binding.nameDet.text = product.name
                binding.desDet.text = product.description
                binding.priceDet.text = product.price.toString() + " tenge"
                binding.quantutyDet.text = product.quantity.toString() + " shtuk"

                val callSeller = service.getSellerById(product.seller)
                callSeller.enqueue(object : Callback<Seller>{
                    override fun onResponse(call: Call<Seller>, response: Response<Seller>) {
                        binding.companuNameDet.text = response.body()!!.company_name
                    }
                    override fun onFailure(call: Call<Seller>, t: Throwable) {}
                })

                ratingCall.enqueue(object: Callback<List<Rating>> {
                    override fun onResponse(call: Call<List<Rating>>, response: Response<List<Rating>>) {
                        var ratings = response.body() as ArrayList<Rating>
                        var sum = 0.0
                        ratings.forEach {
                            sum += it.rating_number
                        }
                        if(ratings.size != 0) {
                            val number = sum / ratings.size.toDouble()
                            val rounded = number.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
                            binding.ratingDet.text =
                                "Rating of this product: ${rounded}"
                        }
                        else binding.ratingDet.text = "Rating of this product: 0"
                    }
                    override fun onFailure(call: Call<List<Rating>>, t: Throwable) {
                        Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })

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