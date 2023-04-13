package com.example.remoteshop.backend

import com.example.remoteshop.backend.users.Admin
import com.example.remoteshop.backend.users.Client
import com.example.remoteshop.backend.users.Seller
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

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

}