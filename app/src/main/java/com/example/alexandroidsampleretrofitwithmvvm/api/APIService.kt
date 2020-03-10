package com.example.androidnamechk.api

import com.example.alexandroidsampleretrofitwithmvvm.model.ResponseLoginUserSuccess
import retrofit2.Call
import retrofit2.http.*


interface APIService {


    @POST("login")
    @FormUrlEncoded
    fun  login (
        @Field("email")   email:String ,
        @Field("password")   password :String
    ): Call<ResponseLoginUserSuccess>


    @POST("auth/login")
    @FormUrlEncoded
    fun  login2 (

        @Field("country_id")   country_id:Int ,
        @Field("phone_number")   phone_number :String ,
        @Field("password")   password :String


    ): Call<ResponseLoginUserSuccess>




}