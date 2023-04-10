package com.example.remoteshop.backend

import com.example.remoteshop.backend.users.Client
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

//    @Field("id") id:Int,
//    @Field("useranme") username: String,
//    @Field("email") email: String,
//    @Field("city") city:String,
//    @Field("password") password: String,

    @POST("createClient/")
    suspend fun createClient(
        @Body client: Client
    ): Response<ResponseBody>

}