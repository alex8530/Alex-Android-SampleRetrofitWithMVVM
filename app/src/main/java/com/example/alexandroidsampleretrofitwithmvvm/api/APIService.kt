package com.example.androidnamechk.api

import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseGetStore
import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseLoginUser
import retrofit2.Call
import retrofit2.http.*


interface APIService {



    @POST("auth/login")
    @FormUrlEncoded
    fun  login2 (

        @Field("country_id")   country_id:Int ,
        @Field("phone_number")   phone_number :String ,
        @Field("password")   password :String


    ): Call<ResponseLoginUser>


     @GET("store/index")
    fun  getStores (
         @Query("page")   page :String

         ): Call<ResponseGetStore>




}