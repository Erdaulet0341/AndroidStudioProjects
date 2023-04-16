package com.example.remoteshop.backend

import android.util.Log
import com.example.remoteshop.backend.products.Category
import com.example.remoteshop.backend.products.Product
import com.example.remoteshop.backend.users.Admin
import com.example.remoteshop.backend.users.Client
import com.example.remoteshop.backend.users.Seller
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface api_services {

    @GET("clients")
    fun getAllClients(): Call<List<Client>>


    @POST("createClient/")
    suspend fun createClient(
        @Body client: Client
    ): Response<ResponseBody>

    @GET("sellers")
    fun getAllSellers(): Call<List<Seller>>

    @POST("createSeller/")
    suspend fun createSeller(
        @Body seller: Seller
    ): Response<ResponseBody>

    @GET("admins")
    fun getAllAdmins(): Call<List<Admin>>

    @GET("SellerById/{id}")
    fun getSellerById(@Path("id") id:Int): Call<Seller>

    @PUT("SellerById/{id}/")
    fun updateSeller(@Path("id") id:Int,  @Body seller: Seller): Call<Void>

    @DELETE("SellerById/{id}/")
    fun deleteSeller(@Path("id") id:Int): Call<ResponseBody>

    @DELETE("clientById/{id}/")
    fun deleteClient(@Path("id") id:Int): Call<ResponseBody>


    @GET("products")
    fun getAllProducts(): Call<List<Product>>

    @GET("productById/{id}")
    fun getProductById(@Path("id") id:Int): Call<Product>

    @PUT("productById/{id}/")
    fun updateProduct(@Path("id") id:Int,  @Body product: Product): Call<Void>

    @DELETE("productById/{id}/")
    fun deleteProduct(@Path("id") id:Int): Call<ResponseBody>

    @GET("SellerById/{seller}/products")
    fun getSelletProducts(@Path("seller") id:Int): Call<List<Product>>

    @POST("products/")
    suspend fun addProduct(
        @Body product: Product
    ): Response<ResponseBody>

    @GET("categories")
    fun getAllCategories(): Call<List<Category>>

    @GET("categotyByName/{name}")
    fun getCategory(@Path("name") name:String): Call<Category>
}